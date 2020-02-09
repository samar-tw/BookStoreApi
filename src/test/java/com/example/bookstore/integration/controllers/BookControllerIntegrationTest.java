package com.example.bookstore.integration.controllers;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.response.BookResponse;
import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.utils.builder.BookTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
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
public class BookControllerIntegrationTest extends AbstractControllerIntegrationTest {
    @Autowired
    private BookRepository bookRepository;

    @Before
    public void before() {
        bookRepository.deleteAll();
    }

    @Test
    public void test_createBook_successfully() throws Exception {
        CreateBookRequest request = new CreateBookRequest("Name", 0.0, 1995, null,
                "Action and Adventure", "Desc");

        MvcResult result = performPost("/book", request)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(result, "$.id");
        Optional<Book> book = bookRepository.findById(id);
        assertTrue(book.isPresent());
        assertEquals(book.get().getName(), request.getName());
    }

    @Test
    public void test_getBook_successfully() throws Exception {
        Book book = BookTestDataBuilder.createBook();
        Book savedBook = bookRepository.save(book);

        performGet("/book/" + savedBook.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBook.getId()))
                .andExpect(jsonPath("$.name").value(savedBook.getName()))
                .andExpect(jsonPath("$.category").value(savedBook.getCategory()))
                .andExpect(jsonPath("$.description").value(savedBook.getDescription()));
    }

    @Test
    public void test_getBook_notFound() throws Exception {

        performGet("/book/1")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_listBook_successfully() throws Exception {
        Book book = new Book();
        book.setId(5);
        book.setName("name1");
        book.setDescription("Desc1");
        book.setCategory("Action and Adventure");

        Book book1 = new Book();
        book1.setId(6);
        book1.setName("name2");
        book1.setDescription("Desc1");
        book1.setCategory("Science fiction");

        List<Book> storedBookList = new ArrayList<>();
        storedBookList.add(book);
        storedBookList.add(book1);

        bookRepository.saveAll(storedBookList);

        MvcResult result = performGet("/book")
                .andExpect(status().isOk())
                .andReturn();

        List<BookResponse> bookList = getListFromMvcResult(result, BookResponse.class);

        assertEquals(storedBookList.size(), bookList.size());
        assertEquals(storedBookList.get(0).getName(), bookList.get(0).getName());
        assertEquals(storedBookList.get(1).getName(), bookList.get(1).getName());
    }

    @Test
    public void test_updateBook_successfully() throws Exception {
        Book testBook = new Book();
        testBook.setName("name1");
        testBook.setDescription("Desc1");
        testBook.setCategory("Action and Adventure");
        testBook.setPublishedYear(1995);

        Book savedBook = bookRepository.save(testBook);

        savedBook.setName("name2");

        performPatch("/book", savedBook)
                .andExpect(status().isOk());

        Optional<Book> updatedBook = bookRepository.findById(savedBook.getId());

        assertTrue(updatedBook.isPresent());
        assertEquals(savedBook.getName(), updatedBook.get().getName());
        assertEquals(savedBook.getDescription(), updatedBook.get().getDescription());
        assertEquals(savedBook.getCategory(), updatedBook.get().getCategory());
    }

    @Test
    public void test_updateBook_idIsNull() throws Exception {
        Book testBook = new Book();
        testBook.setName("name1");
        testBook.setDescription("Desc1");
        testBook.setCategory("Action and Adventure");

        performPatch("/book", testBook)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_updateBook_notFound() throws Exception {
        Book testBook = BookTestDataBuilder.createBook(1);

        performPatch("/book", testBook)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_deleteBook_successfully() throws Exception {
        Book testBook = BookTestDataBuilder.createBook();
        Book savedBook = bookRepository.save(testBook);

        performDelete("/book", Pair.of("id", String.valueOf(savedBook.getId())))
                .andExpect(status().isOk());

        Optional<Book> deletedBook = bookRepository.findById(savedBook.getId());

        assertTrue(deletedBook.isEmpty());
    }

    @Test
    public void test_deleteBook_notFound() throws Exception {
        performDelete("/book", Pair.of("id", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
