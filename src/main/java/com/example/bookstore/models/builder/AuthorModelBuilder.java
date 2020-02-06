package com.example.bookstore.models.builder;

import com.example.bookstore.models.Author;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class AuthorModelBuilder {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Author build() {
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        author.setCreatedAt(createdAt);
        author.setUpdatedAt(updatedAt);

        return author;
    }

    public AuthorModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AuthorModelBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AuthorModelBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AuthorModelBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public AuthorModelBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AuthorModelBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}