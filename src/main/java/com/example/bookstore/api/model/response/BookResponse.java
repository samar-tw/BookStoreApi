package com.example.bookstore.api.model.response;

import com.example.bookstore.models.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class BookResponse {

    private int id;
    private String name;
    private double price;
    private int publishedYear;
    private String category;
    private String description;

    @JsonIgnoreProperties("books")
    private Set<AuthorResponse> authors;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    public BookResponse() {
    }

    public BookResponse(Book book) {
        this(book, true);
    }

    public BookResponse(Book book, boolean returnAuthors) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.publishedYear = book.getPublishedYear();
        this.category = book.getCategory();
        this.description = book.getDescription();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();

        this.authors = returnAuthors && book.getBookAuthors() != null ?
                book.getBookAuthors().stream()
                        .map(author -> new AuthorResponse(author, false))
                        .collect(Collectors.toSet())
                : null;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public double getPrice() {
        return price;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Set<AuthorResponse> getAuthors() {
        return authors;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", publishedYear=" + publishedYear +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
