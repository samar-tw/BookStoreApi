package com.example.bookstore.unit.controllers;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.api.model.response.AuthorResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.controllers.AuthorController;
import com.example.bookstore.models.Author;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.utils.AuthorTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Ehtiram_Abdullayev on 2/9/2020
 * @project book-store
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    public void test_createAuthor_successfully() {
        when(authorService.create(any(CreateAuthorRequest.class))).thenReturn(1);

        CreateAuthorRequest request = AuthorTestDataBuilder.createAuthorRequest();
        CreateEntityResponse response = authorController.createAuthor(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void test_listAuthor_successfully() {
        Author author = AuthorTestDataBuilder.createAuthor();
        author.setId(5);

        when(authorService.findById(anyInt())).thenReturn(author);

        AuthorResponse response = authorController.getAuthor(5);
        assertThat(response.getId()).isSameAs(5);
    }

    @Test
    public void test_listAuthors_successfully() {
        List<Author> storedAuthorList = AuthorTestDataBuilder.createAuthorList(true);

        when(authorService.list()).thenReturn(storedAuthorList);

        List<AuthorResponse> authorList = authorController.getAuthorList();

        assertThat(storedAuthorList.size()).isSameAs(authorList.size());
        assertThat(storedAuthorList.get(0).getId()).isSameAs(authorList.get(0).getId());
        assertThat(storedAuthorList.get(1).getId()).isSameAs(authorList.get(1).getId());
    }

    @Test
    public void test_deleteAuthor_successfully() {
        doNothing().when(authorService).delete(anyInt());

        authorController.deleteAuthor(5);
    }
}
