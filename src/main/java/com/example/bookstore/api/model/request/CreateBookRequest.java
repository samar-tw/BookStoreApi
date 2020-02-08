package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class CreateBookRequest {

    @NotBlank(message = "{bookName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_BOOK_NAME, message = "{bookName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}")
    private String name;

    @PositiveOrZero(message = "{price.positiveOrZero}")
    private double price;

    @Positive(message = "{publishedYear.positive}")
    private int publishedYear;

    private List<Integer> authorIds;


    @NotBlank(message = "{category.notBlank}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME)
    @Size(max = ValidationConfig.MAX_LENGTH_NAME)
    private String category;

    @NotBlank(message = "{description.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT, message = "{description.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{description.pattern}")
    private String description;

    public CreateBookRequest() {
    }

    public CreateBookRequest(String name, double price, int publishedYear,
                             List<Integer> authorIds, String category, String description) {
        this.name = name;
        this.price = price;
        this.publishedYear = publishedYear;
        this.authorIds = authorIds;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
