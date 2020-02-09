package com.example.bookstore.controllers;

import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.SearchByBookNameRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.api.model.response.BookStockResponse;
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.services.BookStockService;
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
@RequestMapping(value = "/bookShelf")
public class BookStockController {
    private final BookStockService bookStockService;

    public BookStockController(BookStockService bookStockService) {
        this.bookStockService = bookStockService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookStockResponse> getBookStockList() {
        List<BookStock> list = bookStockService.getBooksAtShelf();
        return list.stream()
                .map(BookStockResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public BookStockResponseWithBookCountResponse getBook(@PathVariable(name = "bookId") Integer bookId) {
        BookStockResponseWithBookCountResponse response =
                bookStockService.getBookInShelveByBookIdWithAvailableBookCount(bookId);
        return response;
    }

    @RequestMapping(value = "/book/name", method = RequestMethod.POST)
    public BookStockResponseWithBookCountResponse getBookByBookByBookName(@Valid @RequestBody SearchByBookNameRequest bookNameRequest) {
        BookStockResponseWithBookCountResponse response =
                bookStockService.getBookInShelveByBookNameWithAvailableBookCount(bookNameRequest.getBookName());
        return response;
    }

    // adding the physical book to shelf in store
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CreateEntityResponse saveBookToShelf(@Valid @RequestBody CreateBookStockRequest request) {
        Integer id = bookStockService.addBookToTheShelfInStore(request);
        return new CreateEntityResponse(id);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void updateBookAtShelf(@Valid @RequestBody UpdateBookStockRequest request) {
        bookStockService.updateBookInShelf(request);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBookStock(@NotNull(message = "Id should not be null!") @RequestParam(name = "id") Integer id) {
        bookStockService.deleteBookInShelve(id);
    }

}
