package com.example.bookstore.unit.services;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.Author;
import com.example.bookstore.repositories.AuthorRepository;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.utils.AuthorTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void test_createAuthor_successfully() {
        CreateAuthorRequest request = AuthorTestDataBuilder.createAuthorRequest();
        Author response = AuthorTestDataBuilder.createAuthor();
        response.setId(5);

        when(authorRepository.save(any(Author.class))).thenReturn(response);
        Integer id = authorService.create(request);
        assertThat(id).isSameAs(response.getId());
    }
    @Test
    public void test_deleteAuthor_successfully() {
        when(authorRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(authorRepository).deleteById(anyInt());

        authorService.delete(5);
    }

    @Test(expected = NotFoundException.class)
    public void test_deleteAuthor_notFound() {
        when(authorRepository.existsById(anyInt())).thenReturn(false);
        authorService.delete(1);
    }

}
