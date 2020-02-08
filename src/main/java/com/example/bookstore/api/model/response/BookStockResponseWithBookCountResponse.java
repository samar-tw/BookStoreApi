package com.example.bookstore.api.model.response;

import java.util.List;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class BookStockResponseWithBookCountResponse {
    private List<BookStockResponse> bookStocks;
    private int numberOfBooks;

    public BookStockResponseWithBookCountResponse() {
    }

    public BookStockResponseWithBookCountResponse(List<BookStockResponse> bookStocks,
                                                  int numberOfBooks) {
        this.bookStocks = bookStocks;
        this.numberOfBooks = numberOfBooks;
    }

    public List<BookStockResponse> getBookStocks() {
        return bookStocks;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }
}
