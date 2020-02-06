package com.example.bookstore.utils.builder;

import com.example.bookstore.api.model.request.CreateAuthorRequest;

import java.time.LocalDate;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class CreateAuthorRequestBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public CreateAuthorRequest build() {
        return new CreateAuthorRequest(firstName, lastName, birthDate);
    }

    public CreateAuthorRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateAuthorRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateAuthorRequestBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
