package com.example.bookstore.controllers;

import io.swagger.annotations.*;
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

@Api(tags = "Author Management", description = "Operations about authors")
@RestController
@RequestMapping(value = "/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "Get all authors", notes = "Returns a list of all authors in the system")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of authors"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<AuthorResponse> getAuthorList() {
        List<Author> list = authorService.list();
        return list.stream()
                .map(AuthorResponse::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get author by ID", notes = "Returns a single author")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved author"),
        @ApiResponse(code = 404, message = "Author not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AuthorResponse getAuthor(
            @ApiParam(value = "ID of author to return", required = true)
            @PathVariable(name = "id") Integer id) {
        Author author = authorService.findById(id);
        return new AuthorResponse(author);
    }

    @ApiOperation(value = "Create a new author", notes = "Creates a new author in the system")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Author successfully created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEntityResponse createAuthor(
            @ApiParam(value = "Author object that needs to be created", required = true)
            @Valid @RequestBody CreateAuthorRequest request) {
        Integer id = authorService.create(request);
        return new CreateEntityResponse(id);
    }

    @ApiOperation(value = "Delete an author", notes = "Deletes an author from the system")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Author successfully deleted"),
        @ApiResponse(code = 404, message = "Author not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAuthor(
            @ApiParam(value = "ID of author to delete", required = true)
            @NotNull @RequestParam(name = "id") Integer id) {
        authorService.delete(id);
    }
}