package com.example.bookstore.integration.controllers;

import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.SearchByBookNameRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.api.model.response.BookStockResponse;
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.BookStockRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.test.web.servlet.MvcResult;

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
public class BookStockControllerIntegrationTest extends AbstractControllerIntegrationTest {
    @Autowired
    private BookStockRepository bookStockRepository;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void before() {
        bookStockRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void test_createBookStock_successfully() throws Exception {
        Book book = new Book();
        book.setName("name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");
        Book savedBook = bookRepository.save(book);

        CreateBookStockRequest request = new CreateBookStockRequest(savedBook.getId(), "1123456789");

        MvcResult result = performPost("/bookShelf", request)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(result, "$.id");
        Optional<BookStock> bookStock = bookStockRepository.findById(id);
        assertTrue(bookStock.isPresent());
        assertEquals(bookStock.get().getBook().getName(), savedBook.getName());
    }

    @Test
    public void test_getBookStock_successfully() throws Exception {
        Book book = new Book();
        book.setName("name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");
        bookRepository.save(book);

        BookStock response = new BookStock();
        response.setBook(book);
        response.setIsbn("1-123456789");
        BookStock savedBookStock = bookStockRepository.save(response);

        performGet("/bookShelf/book/" + book.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookStocks[0].id").value(savedBookStock.getId()))
                .andExpect(jsonPath("$.bookStocks[0].book.name").value(savedBookStock.getBook().getName()))
                .andExpect(jsonPath("$.bookStocks[0].book.category").value(savedBookStock.getBook().getCategory()))
                .andExpect(jsonPath("$.bookStocks[0].book.description").value(savedBookStock.getBook().getDescription()));
    }
    @Test
    public void test_getBookStock_notFound() throws Exception {
        performGet("/bookShelf/book/1")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_listBookStock_successfully() throws Exception {
        Book book = new Book();
        book.setId(5);
        book.setName("name1");
        book.setCategory("Science fiction");
        book.setDescription("desc1");

        Book book1 = new Book();
        book1.setId(6);
        book1.setName("name2");
        book1.setCategory("Science fiction");
        book1.setDescription("desc2");

        Book savedBook1 = bookRepository.save(book);
        Book savedBook2 = bookRepository.save(book1);

        BookStock bookStock = new BookStock();
        bookStock.setBook(savedBook1);

        BookStock bookStock1 = new BookStock();
        bookStock1.setBook(savedBook2);

        List<BookStock> storedBookStockList = new ArrayList<>();
        storedBookStockList.add(bookStock);
        storedBookStockList.add(bookStock1);

        bookStockRepository.saveAll(storedBookStockList);

        MvcResult result = performGet("/bookShelf")
                .andExpect(status().isOk())
                .andReturn();

        List<BookStockResponse> bookStockResponseList = getListFromMvcResult(result, BookStockResponse.class);

        assertEquals(storedBookStockList.size(), bookStockResponseList.size());
        assertEquals(storedBookStockList.get(0).getBook().getName(), bookStockResponseList.get(0).getBook().getName());
        assertEquals(storedBookStockList.get(1).getBook().getName(), bookStockResponseList.get(1).getBook().getName());
    }

    @Test
    public void test_updateBookStock_successfully() throws Exception {
        Book book = new Book();
        book.setName("Name");
        book.setCategory("Science fiction");
        book.setDescription("Desc");
        Book savedBook = bookRepository.save(book);

        BookStock bookStock = new BookStock();
        bookStock.setBook(savedBook);
        bookStock.setIsbn("1123456789");
        BookStock savedBookStock = bookStockRepository.save(bookStock);

        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest(savedBookStock.getId(), "1789456123");

        performPatch("/bookShelf", updateBookStockRequest)
                .andExpect(status().isOk());

        Optional<BookStock> updatedBookStock = bookStockRepository.findById(savedBookStock.getId());

        assertTrue(updatedBookStock.isPresent());
        assertEquals(savedBookStock.getBook().getName(), updatedBookStock.get().getBook().getName());
        assertEquals(savedBookStock.getBook().getDescription(), updatedBookStock.get().getBook().getDescription());
        assertEquals(savedBookStock.getBook().getCategory(), updatedBookStock.get().getBook().getCategory());
    }

    @Test
    public void test_updateBookStock_idIsNull() throws Exception {
        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest(null, "1-789456123");

        performPatch("/bookShelf", updateBookStockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_updateBookStock_notFound() throws Exception {
        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest(3, "1789456123");

        performPatch("/bookShelf", updateBookStockRequest)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_deleteBookStock_successfully() throws Exception {
        Book testBook = new Book();
        testBook.setName("name1");
        testBook.setDescription("Desc1");
        testBook.setCategory("Action and Adventure");
        Book savedBook = bookRepository.save(testBook);

        BookStock response = new BookStock();
        response.setBook(savedBook);
        response.setId(5);
        BookStock savedBookStock= bookStockRepository.save(response);

        performDelete("/bookShelf", Pair.of("id", String.valueOf(savedBookStock.getId())))
                .andExpect(status().isOk());

        Optional<BookStock> deletedStock = bookStockRepository.findById(savedBookStock.getId());
        assertTrue(deletedStock.isEmpty());
    }

    @Test
    public void test_deleteBookStock_notFound() throws Exception {
        performDelete("/bookShelf", Pair.of("id", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_getBookByName_foundSuccessfully() throws Exception {
        Book testBook = new Book();
        testBook.setName("name1");
        testBook.setDescription("Desc1");
        testBook.setCategory("Action and Adventure");
        Book savedBook = bookRepository.save(testBook);

        BookStock response = new BookStock();
        response.setBook(savedBook);
        response.setId(5);
        BookStock savedBookStock = bookStockRepository.save(response);

        SearchByBookNameRequest titleRequest = new SearchByBookNameRequest(savedBook.getName());

        MvcResult result = performPost("/bookShelf/book/name/", titleRequest)
                .andExpect(status().isOk()).andReturn();

        BookStockResponseWithBookCountResponse mvcResult =
                getFromMvcResult(result, BookStockResponseWithBookCountResponse.class);

        assertEquals(savedBookStock.getBook().getName(),mvcResult.getBookStocks().get(0).getBook().getName());
        assertEquals(mvcResult.getBookStocks().size(),1);
    }

    @Test
    public void test_getBookByBookName_notFound() throws Exception {
        Book testBook = new Book();
        testBook.setName("name1");
        testBook.setDescription("Desc1");
        testBook.setCategory("Action and Adventure");
        Book savedBook = bookRepository.save(testBook);

        BookStock response = new BookStock();
        response.setBook(savedBook);
        response.setId(5);

        bookStockRepository.save(response);

        SearchByBookNameRequest titleRequest = new SearchByBookNameRequest("Book name");

        performPost("/bookShelf/book/name/", titleRequest)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
