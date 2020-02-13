package com.example.bookstore.unit.services;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.BookStockRepository;
import com.example.bookstore.services.BookStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Ehtiram_Abdullayev on 2/9/2020
 * @project book-store
 */
@RunWith(MockitoJUnitRunner.class)
public class BookStockServiceTest {
    @Mock
    private BookStockRepository bookStockRepository;

    @InjectMocks
    private BookStockService bookStockService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void test_createBookStock_successfully() {
        CreateBookRequest request = new CreateBookRequest("name", 0.0, 0, null, "Action and Adventure", "Desc");

        Book book = new Book();
        book.setId(5);
        book.setName("name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");

        BookStock bookStock = new BookStock();
        bookStock.setId(1);
        bookStock.setBook(book);

        CreateBookStockRequest createBookStockRequest = new CreateBookStockRequest(5, "1-123-456-789");

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        when(bookStockRepository.save(any(BookStock.class))).thenReturn(bookStock);

        Integer id = bookStockService.addBookToTheShelfInStore(createBookStockRequest);
        assertThat(id).isSameAs(bookStock.getId());
    }
    @Test
    public void test_updateBookStock_successfully() {
        Book book = new Book();
        book.setName("Name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");

        BookStock existingBookStock = new BookStock();
        existingBookStock.setId(5);

        UpdateBookStockRequest updateBookStockRequest = new UpdateBookStockRequest(5, "1-123-456-789");

        when(bookStockRepository.save(any(BookStock.class))).thenReturn(existingBookStock);
        when(bookStockRepository.findById(anyInt())).thenReturn(Optional.of(existingBookStock));

        bookStockService.updateBookInShelf(updateBookStockRequest);
    }

    @Test(expected = NotFoundException.class)
    public void test_updateBookStock_notFound() {
        when(bookStockRepository.findById(anyInt())).thenReturn(Optional.empty());
        bookStockService.updateBookInShelf(new UpdateBookStockRequest(1, "1-234-56-789"));
    }

    @Test
    public void test_deleteBookStock_successfully() {
        when(bookStockRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(bookStockRepository).deleteById(anyInt());
        bookStockService.deleteBookInShelve(1);
    }

    @Test(expected = NotFoundException.class)
    public void test_deleteBookStock_notFound() {
        when(bookStockRepository.existsById(anyInt())).thenReturn(false);
        bookStockService.deleteBookInShelve(1);
    }

    @Test
    public void test_getBookByName_foundSuccessfully() {
        Book book = new Book();
        book.setId(5);
        book.setName("Name");
        book.setCategory("Category");
        book.setDescription("Desc");

        BookStock bookStock1 = new BookStock();
        bookStock1.setId(1);
        bookStock1.setIsbn("1-123456788");
        bookStock1.setBook(book);

        BookStock bookStock2 = new BookStock();
        bookStock2.setId(2);
        bookStock1.setIsbn("1-123456789");
        bookStock2.setBook(book);

        when(bookStockRepository.getBookStocksByBookNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(bookStock1, bookStock2));

        List<BookStock> list = bookStockService.getBookStocksByBookByName("Name");

        assertThat(list.size()).isSameAs(2);
    }

    @Test(expected = NotFoundException.class)
    public void test_getBookByName_notFound() {
        when(bookStockRepository.getBookStocksByBookNameContainingIgnoreCase(anyString())).thenReturn(Collections.emptyList());
        bookStockService.getBookInShelveByBookNameWithAvailableBookCount("someText");
    }

    @Test(expected = NotFoundException.class)
    public void test_getBookByNameWhenBookIsNull_notFound() {
        bookStockService.getBookInShelveByBookNameWithAvailableBookCount(null);
    }
}
