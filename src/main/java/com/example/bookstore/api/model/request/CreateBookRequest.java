package com.example.bookstore.api.model.request;

import java.util.List;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
public class CreateBookRequest {

    private String name;

    private double price;

    private int publishedYear;

    private List<Integer> authorIds;

    private String category;

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
