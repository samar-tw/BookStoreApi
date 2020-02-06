package com.example.bookstore.utils;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.builder.AuthorModelBuilder;
import com.example.bookstore.utils.builder.CreateAuthorRequestBuilder;

import java.time.LocalDate;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
public class AuthorTestDataBuilder {
    public static CreateAuthorRequest createAuthorRequest() {
        return new CreateAuthorRequestBuilder()
                .withFirstName("Ehtiram")
                .withLastName("Abdullayev")
                .withBirthDate(LocalDate.parse("1994-05-06"))
                .build();
    }

    public static Author createAuthor() {
        return new AuthorModelBuilder()
                .withFirstName("Ehtiram")
                .withLastName("Ehtiram")
                .withBirthDate(LocalDate.parse("1994-05-06"))
                .build();
    }
}
