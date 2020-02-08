package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class SearchByBookNameRequest {

    @NotBlank(message = "{bookName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_BOOK_NAME, message = "{bookName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}")
    private String bookName;

    public SearchByBookNameRequest() {
    }

    public SearchByBookNameRequest(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}
