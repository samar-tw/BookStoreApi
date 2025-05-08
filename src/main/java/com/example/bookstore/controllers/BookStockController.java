package com.example.bookstore.controllers;

import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.SearchByBookNameRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.api.model.response.BookStockResponse;
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.services.BookStockService;
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
@RequestMapping(value = "/bookShelf")
@Api(tags = "Book Stock Controller", description = "Manage book stock at the shelf level")
public class BookStockController {
    private final BookStockService bookStockService;

    public BookStockController(BookStockService bookStockService) {
        this.bookStockService = bookStockService;
    }

    @ApiOperation(value = "Get list of all books at the shelf", response = BookStockResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book stocks"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<BookStockResponse> getBookStockList() {
        List<BookStock> list = bookStockService.getBooksAtShelf();
        return list.stream()
                .map(BookStockResponse::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get book stock details with available count by Book ID", response = BookStockResponseWithBookCountResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book stock details"),
            @ApiResponse(code = 404, message = "Book stock not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public BookStockResponseWithBookCountResponse getBook(
            @ApiParam(value = "ID of the book to retrieve stock details", required = true)
            @PathVariable(name = "bookId") Integer bookId) {
        return bookStockService.getBookInShelveByBookIdWithAvailableBookCount(bookId);
    }

    @ApiOperation(value = "Get book stock details by book name", response = BookStockResponseWithBookCountResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book stock by name"),
            @ApiResponse(code = 400, message = "Invalid book name"),
            @ApiResponse(code = 404, message = "Book stock not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/book/name", method = RequestMethod.POST)
    public BookStockResponseWithBookCountResponse getBookByBookByBookName(
            @ApiParam(value = "Request containing the name of the book to search", required = true)
            @Valid @RequestBody SearchByBookNameRequest bookNameRequest) {
        return bookStockService.getBookInShelveByBookNameWithAvailableBookCount(bookNameRequest.getBookName());
    }

    @ApiOperation(value = "Save a new book to the shelf", response = CreateEntityResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book successfully added to shelf"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CreateEntityResponse saveBookToShelf(
            @ApiParam(value = "Request containing book stock details", required = true)
            @Valid @RequestBody CreateBookStockRequest request) {
        Integer id = bookStockService.addBookToTheShelfInStore(request);
        return new CreateEntityResponse(id);
    }

    @ApiOperation(value = "Update an existing book stock at the shelf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book stock successfully updated"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 404, message = "Book stock not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateBookAtShelf(
            @ApiParam(value = "Request containing book stock update details", required = true)
            @Valid @RequestBody UpdateBookStockRequest request) {
        bookStockService.updateBookInShelf(request);
    }

    @ApiOperation(value = "Delete a book stock by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book stock successfully deleted"),
            @ApiResponse(code = 400, message = "Invalid Book ID"),
            @ApiResponse(code = 404, message = "Book stock not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBookStock(
            @ApiParam(value = "ID of the book stock to delete", required = true)
            @NotNull(message = "Id should not be null!") @RequestParam(name = "id") Integer id) {
        bookStockService.deleteBookInShelve(id);
    }
}