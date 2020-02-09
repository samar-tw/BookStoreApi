package com.example.bookstore.controllers;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.UpdateBookRequest;
import com.example.bookstore.api.model.response.BookResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookResponse> getBookList() {
        List<Book> list = bookService.getBookList();

        return list.stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookResponse getBook(@PathVariable(name = "id") Integer id) {
        Book book = bookService.getBookById(id);
        return new BookResponse(book);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createBook(@Valid @RequestBody CreateBookRequest request) {
        Integer id = bookService.createBook(request);
        return new CreateEntityResponse(id);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void updateBook(@Valid @RequestBody UpdateBookRequest updateBookRequest) {
        bookService.updateBook(updateBookRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBook(@NotNull(message = "{id.notNull}") @RequestParam(name = "id") Integer id) {
        bookService.deleteBook(id);
    }


}
