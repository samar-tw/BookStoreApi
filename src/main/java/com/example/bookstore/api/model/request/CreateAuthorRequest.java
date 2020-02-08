package com.example.bookstore.api.model.request;

import com.example.bookstore.config.ValidationConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class CreateAuthorRequest {
    @NotBlank(message = "{firstName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{firstName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{firstName.pattern}")
    private String firstName;

    @NotBlank(message = "{lastName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{lastName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{lastName.pattern}")
    private String lastName;

    @Past(message = "{birthDate.past}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
