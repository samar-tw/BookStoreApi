package com.example.bookstore.utils;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.builder.AuthorModelBuilder;
import com.example.bookstore.utils.builder.CreateAuthorRequestBuilder;

import java.time.LocalDate;
import java.util.List;

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
                .withLastName("Abdullayev")
                .withBirthDate(LocalDate.parse("1994-05-06"))
                .build();
    }
    public static List<Author> createAuthorList() {
        return createAuthorList(false);
    }

    public static List<Author> createAuthorList(boolean withId) {
        Author Author1 = new AuthorModelBuilder()
                .withId(withId ? 10 : null)
                .withFirstName("Sergey")
                .withLastName("Abdullayev")
                .withBirthDate(LocalDate.parse("1980-05-06"))
                .build();

        Author Author2 = new AuthorModelBuilder()
                .withId(withId ? 11 : null)
                .withFirstName("Ruslan")
                .withLastName("Abdullayev")
                .withBirthDate(LocalDate.parse("1980-05-08"))
                .build();

        return List.of(Author1, Author2);
    }
}
