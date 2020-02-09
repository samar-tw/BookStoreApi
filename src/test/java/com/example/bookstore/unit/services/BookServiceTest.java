package com.example.bookstore.unit.services;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.UpdateBookRequest;
import com.example.bookstore.config.MessageProvider;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

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

public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private MessageProvider messageProvider;

    @InjectMocks
    private BookService bookService;

    @Test
    public void test_createBook_successfully() {
        CreateBookRequest request = new CreateBookRequest("Name", 0.0, 0,
                null, "Action and Adventure", "Desc");

        Book response = new Book();
        response.setName("Name");
        response.setCategory("Action and Adventure");
        response.setDescription("Desc");

        when(bookRepository.save(any(Book.class))).thenReturn(response);

        Integer id = bookService.createBook(request);

        assertThat(id).isSameAs(response.getId());
    }

    @Test
    public void test_updateBook_successfully() {
        Book response = new Book();
        response.setName("Name");
        response.setCategory("Action and Adventure");
        response.setDescription("Desc");
        response.setId(5);

        when(bookRepository.save(any(Book.class))).thenReturn(response);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(response));

        UpdateBookRequest updateBookRequest = new UpdateBookRequest(5, "New Name", 0.0, 1999, null,
                "Category", "Desc");
        bookService.updateBook(updateBookRequest);
    }

    @Test(expected = NotFoundException.class)
    public void test_updateBook_notFound() {
        UpdateBookRequest updateBookRequest = new UpdateBookRequest(5, "New Name", 0.0, 1999, null,
                "Category", "Desc");
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());

        bookService.updateBook(updateBookRequest);
    }
    @Test
    public void test_deleteBook_successfully() {
        when(bookRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(bookRepository).deleteById(anyInt());

        bookService.deleteBook(1);
    }

    @Test(expected = NotFoundException.class)
    public void test_deleteBook_notFound() {
        when(bookRepository.existsById(anyInt())).thenReturn(false);

        bookService.deleteBook(1);
    }
}
