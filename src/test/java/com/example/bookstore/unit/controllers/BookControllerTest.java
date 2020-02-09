package com.example.bookstore.unit.controllers;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.UpdateBookRequest;
import com.example.bookstore.api.model.response.BookResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.controllers.BookController;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
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
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void test_createBook_successfully() {
        CreateBookRequest request = new CreateBookRequest("Name", 0.0, 1995, null, "Action and Adventure", "Desc");

        when(bookService.createBook(any(CreateBookRequest.class))).thenReturn(1);

        CreateEntityResponse response = bookController.createBook(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void test_listBook_successfully() {
        Book book = new Book();
        book.setId(5);
        book.setName("name");

        when(bookService.getBookById(anyInt())).thenReturn(book);

        BookResponse response = bookController.getBook(5);

        assertThat(response.getId()).isSameAs(book.getId());
        assertThat(response.getName()).isSameAs(book.getName());
    }

    @Test
    public void test_listBooks_successfully() {
        Book book = new Book();
        book.setId(5);
        book.setName("name1");

        Book book1 = new Book();
        book1.setId(6);
        book1.setName("name2");

        List<Book> storedBookList = new ArrayList<>();
        storedBookList.add(book);
        storedBookList.add(book1);

        when(bookService.getBookList()).thenReturn(storedBookList);

        List<BookResponse> bookList = bookController.getBookList();

        assertThat(storedBookList.size()).isSameAs(bookList.size());
        assertThat(storedBookList.get(0).getId()).isSameAs(bookList.get(0).getId());
        assertThat(storedBookList.get(1).getId()).isSameAs(bookList.get(1).getId());
    }

    @Test
    public void test_updateBook_successfully() {
        Book book = new Book();
        book.setName("name1");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");

        doNothing().when(bookService).updateBook(any());
        book.setName("name2");

        UpdateBookRequest request = new UpdateBookRequest(1, "name", 0.0, 1995, null,
                "Action and Adventure", "Desc");

        bookController.updateBook(request);
    }

    @Test
    public void test_deleteBook_successfully() {
        doNothing().when(bookService).deleteBook(anyInt());
        bookController.deleteBook(5);
    }
}
