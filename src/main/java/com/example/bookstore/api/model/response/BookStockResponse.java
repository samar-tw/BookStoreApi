package com.example.bookstore.api.model.response;

import com.example.bookstore.models.BookStock;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class BookStockResponse {
    private int id;
    private String isbn;

    private BookResponse book;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime bookAddedToShelfAt;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime bookAtShelfUpdatedAt;

    public BookStockResponse() {
    }

    public BookStockResponse(BookStock bookStock) {
        this.id = bookStock.getId();
        this.isbn = bookStock.getIsbn();
        this.book = new BookResponse(bookStock.getBook());
        this.bookAddedToShelfAt = bookStock.getBookAddedToShelfAt();
        this.bookAtShelfUpdatedAt = bookStock.getBookAtShelfUpdatedAt();
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookResponse getBook() {
        return book;
    }

    public LocalDateTime getBookAddedToShelfAt() {
        return bookAddedToShelfAt;
    }

    public LocalDateTime getBookAtShelfUpdatedAt() {
        return bookAtShelfUpdatedAt;
    }

    @Override
    public String toString() {
        return "BookStockResponse{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", book=" + book +
                ", bookAddedToShelfAt=" + bookAddedToShelfAt +
                ", bookAtShelfUpdatedAt=" + bookAtShelfUpdatedAt +
                '}';
    }
}
