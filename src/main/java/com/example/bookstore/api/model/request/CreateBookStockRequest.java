package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class CreateBookStockRequest {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Integer bookId;

    @NotBlank(message = "{isbn.notBlank}")
    @Pattern(regexp = ValidationConfig.PATTERN_ISBN_10_13, message = "{isbn.valid}")
    private String isbn;

    public CreateBookStockRequest() {
    }

    public CreateBookStockRequest(Integer bookId, String isbn) {
        this.bookId = bookId;
        this.isbn = isbn;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "CreateBookStockRequest{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
