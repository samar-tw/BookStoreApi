package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class UpdateBookStockRequest extends BasicRequest {

    @NotBlank(message = "{isbn.notBlank}")
    @Pattern(regexp = ValidationConfig.PATTERN_ISBN_10_13, message = "{isbn.valid}")
    private String isbn;

    public UpdateBookStockRequest() {
    }

    public UpdateBookStockRequest(Integer id, String isbn) {
        super(id);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "UpdateBookStockRequest{" +
                "isbn='" + isbn + '\'' +
                '}';
    }
}
