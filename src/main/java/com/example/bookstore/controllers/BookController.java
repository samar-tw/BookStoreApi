package com.example.bookstore.controllers;

import com.example.bookstore.api.model.response.BookResponse;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
