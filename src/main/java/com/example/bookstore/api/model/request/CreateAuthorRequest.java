package com.example.bookstore.api.model.request;

import java.time.LocalDate;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class CreateAuthorRequest {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public CreateAuthorRequest() {
    }

    public CreateAuthorRequest(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "CreateAuthorRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
