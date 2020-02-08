package com.example.bookstore.services;

import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.Author;
import com.example.bookstore.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import com.example.bookstore.api.model.request.CreateAuthorRequest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> list() {
        return authorRepository.findAll();
    }

    public Author findById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found!"));
    }

    public Integer create(CreateAuthorRequest request) {
        Author author = new Author();

        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setBirthDate(request.getBirthDate());

        Author createdAuthor = authorRepository.save(author);
        return createdAuthor.getId();
    }

    public void delete(int id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found!");
        }
        authorRepository.deleteById(id);
    }

    public Set<Author> getAuthorsByIds(List<Integer> authorIds) {
        return authorIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }
}
