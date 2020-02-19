package com.example.bookstore.integration.controllers;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.api.model.response.AuthorResponse;
import com.example.bookstore.models.Author;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.utils.AuthorTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ehtiram_Abdullayev on 2/9/2020
 * @project book-store
 */
//@TestG
public class AuthorControllerIntegrationTest extends AbstractControllerIntegrationTest{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void before() {
        authorRepository.deleteAll();
    }

    @Test
    //junit5 -- @Tag("integration")
    public void test_createAuthor_successfully() throws Exception {
        CreateAuthorRequest request = AuthorTestDataBuilder.createAuthorRequest();

        MvcResult result = performPost("/author", request)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(result, "$.id");

        Optional<Author> author = authorRepository.findById(id);

        assertTrue(author.isPresent());
        assertEquals(author.get().getFirstName(), request.getFirstName());
    }

    @Test
    public void test_listAuthor_successfully() throws Exception {
        Author testAuthor = AuthorTestDataBuilder.createAuthor();
        Author savedAuthor = authorRepository.save(testAuthor);

        performGet("/author/" + savedAuthor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedAuthor.getId()))
                .andExpect(jsonPath("$.firstName").value(testAuthor.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testAuthor.getLastName()))
                .andExpect(jsonPath("$.birthDate").value(testAuthor.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @Test
    public void test_listAuthor_notFound() throws Exception {
        performGet("/author/1")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_listAuthors_successfully() throws Exception {


        List<Author> storedAuthorList = AuthorTestDataBuilder.createAuthorList();
        authorRepository.saveAll(storedAuthorList);

        MvcResult result = performGet("/author")
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorResponse> listAuthorsResponse = getListFromMvcResult(result, AuthorResponse.class);

        assertEquals(storedAuthorList.size(), listAuthorsResponse.size());
        assertTrue(storedAuthorList.stream()
                .map(AuthorResponse::new)
                .allMatch(listAuthorsResponse::contains));
    }



    @Test
    public void test_deleteAuthor_successfully() throws Exception {
        Author testAuthor = AuthorTestDataBuilder.createAuthor();
        Author savedAuthor = authorRepository.save(testAuthor);

        performDelete("/author?id="+savedAuthor.getId())
                .andExpect(status().isOk());

        Optional<Author> deletedAuthor = authorRepository.findById(savedAuthor.getId());
        assertTrue(deletedAuthor.isEmpty());
    }

    @Test
    public void test_deleteAuthor_notFound() throws Exception {

        performDelete("/author?id=11")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
