package com.example.bookstore.controllers;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.UpdateBookRequest;
import com.example.bookstore.api.model.response.BookResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev
 * @project book-store
 */
@RestController
@RequestMapping(value = "/book")
@Api(tags = "Book Controller", description = "CRUD operations for Books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Get a list of all books", response = BookResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of books"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<BookResponse> getBookList() {
        List<Book> list = bookService.getBookList();

        return list.stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get details of a specific book by ID", response = BookResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the book"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookResponse getBook(
            @ApiParam(value = "ID of the book to retrieve", required = true) 
            @PathVariable(name = "id") Integer id) {
        Book book = bookService.getBookById(id);
        return new BookResponse(book);
    }

    @ApiOperation(value = "Create a new book", response = CreateEntityResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the book"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createBook(
            @ApiParam(value = "Details of the book to create", required = true) 
            @Valid @RequestBody CreateBookRequest request) {
        Integer id = bookService.createBook(request);
        return new CreateEntityResponse(id);
    }

    @ApiOperation(value = "Update an existing book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the book"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateBook(
            @ApiParam(value = "Details of the book to update", required = true) 
            @Valid @RequestBody UpdateBookRequest updateBookRequest) {
        bookService.updateBook(updateBookRequest);
    }

    @ApiOperation(value = "Delete a book by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the book"),
            @ApiResponse(code = 400, message = "Invalid ID"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBook(
            @ApiParam(value = "ID of the book to delete", required = true) 
            @NotNull(message = "{id.notNull}") @RequestParam(name = "id") Integer id) {
        bookService.deleteBook(id);
    }
}