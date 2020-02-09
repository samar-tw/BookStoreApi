package com.example.bookstore.unit.controllers;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.SearchByBookNameRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.api.model.response.BookStockResponse;
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.controllers.BookStockController;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.services.BookStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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
public class BookStockControllerTest {

    @Mock
    private BookStockService bookStockService;

    @InjectMocks
    private BookStockController bookStockController;

    @Test
    public void test_createBookStock_successfully() {
        CreateBookStockRequest request = new CreateBookStockRequest(1, "123456789X");

        when(bookStockService.addBookToTheShelfInStore(any(CreateBookStockRequest.class))).thenReturn(1);

        CreateEntityResponse responseEntity = bookStockController.saveBookToShelf(request);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getId()).isSameAs(1);
    }



    @Test
    public void test_listBookStocks_successfully() {
        Book book = new Book();
        book.setId(5);
        book.setName("name1");

        Book book1 = new Book();
        book1.setId(6);
        book1.setName("name2");

        BookStock bookStock = new BookStock();
        bookStock.setBook(book);
        bookStock.setId(5);

        BookStock bookStock1 = new BookStock();
        bookStock1.setBook(book1);
        bookStock1.setId(6);

        List<BookStock> storedBookStockList = new ArrayList<>();
        storedBookStockList.add(bookStock);
        storedBookStockList.add(bookStock1);

        when(bookStockService.getBooksAtShelf()).thenReturn(storedBookStockList);

        List<BookStockResponse> bookStockList = bookStockController.getBookStockList();

        assertThat(storedBookStockList.size()).isSameAs(bookStockList.size());
        assertThat(storedBookStockList.get(0).getId()).isSameAs(bookStockList.get(0).getId());
        assertThat(storedBookStockList.get(1).getId()).isSameAs(bookStockList.get(1).getId());
    }

    @Test
    public void test_updateBookStock_successfully() {
        CreateBookRequest request = new CreateBookRequest("Title", 0.0, 0, null, "Action and Adventure", "Desc");

        Book book = new Book();
        book.setName("Name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");

        BookStock response = new BookStock();
        response.setBook(book);
        response.setId(5);

        doNothing().when(bookStockService).updateBookInShelf(any());

        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest(1, "123456789X");

        bookStockController.updateBookAtShelf(updateBookStockRequest);
    }
    @Test
    public void test_deleteBookStock_successfully() {
        doNothing().when(bookStockService).deleteBookInShelve(anyInt());

        bookStockController.deleteBookStock(5);
    }

    @Test
    public void test_getBookByTitle_foundSuccessfully() {
        Book book = new Book();
        book.setName("Name");
        book.setCategory("Category");
        book.setDescription("Desc");
        book.setId(1);

        BookStock bookStock = new BookStock();
        bookStock.setBook(book);
        bookStock.setId(5);

        BookStockResponseWithBookCountResponse bookStockResponseWithBookCountResponse
                = new BookStockResponseWithBookCountResponse(List.of(new BookStockResponse(bookStock)), 1);

        when(bookStockService.getBookInShelveByBookNameWithAvailableBookCount(any())).thenReturn(bookStockResponseWithBookCountResponse);

        SearchByBookNameRequest request = new SearchByBookNameRequest("Name");
        BookStockResponseWithBookCountResponse response = bookStockController.getBookByBookByBookName(request);

        assertThat(response.getNumberOfBooks()).isSameAs(1);
    }
}
