package com.example.bookstore.controllers;

import com.example.bookstore.api.model.request.CreateAuthorRequest;
import com.example.bookstore.api.model.response.AuthorResponse;
import com.example.bookstore.api.model.response.CreateEntityResponse;
import com.example.bookstore.models.Author;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@RestController
@RequestMapping(value = "/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AuthorResponse> getAuthorList() {
        List<Author> list = authorService.list();

        return list
                .stream()
                .map(AuthorResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AuthorResponse getAuthor(@PathVariable(name = "id") Integer id) {
        Author author = authorService.findById(id);
        return new AuthorResponse(author);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        Integer id = authorService.create(request);
        return new CreateEntityResponse(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAuthor(@NotNull @RequestParam(name = "id") Integer id) {
        authorService.delete(id);
    }

}
