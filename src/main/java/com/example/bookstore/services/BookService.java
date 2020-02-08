package com.example.bookstore.services;

import com.example.bookstore.api.model.request.CreateBookRequest;
import com.example.bookstore.api.model.request.UpdateBookRequest;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found!"));
    }

    public void updateBook(UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findById(updateBookRequest.getId())
                .orElseThrow(() -> new NotFoundException("Book is not found!"));

        Set<Author> authors = Optional.ofNullable(updateBookRequest.getAuthorIds())
                .map(authorService::getAuthorsByIds)
                .orElse(null);

        book.setName(updateBookRequest.getName());
        book.setCategory(updateBookRequest.getCategory());
        book.setDescription(updateBookRequest.getDescription());
        book.setPrice(updateBookRequest.getPrice());
        book.setPublishedYear(updateBookRequest.getPublishedYear());

        if (authors != null) {
            book.setBookAuthors(authors);
        }

        bookRepository.save(book);
    }

    public Integer createBook(CreateBookRequest createBookRequest) {
        Set<Author> authors = Optional.ofNullable(createBookRequest.getAuthorIds())
                .map(authorService::getAuthorsByIds)
                .orElse(null);

        Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setCategory(createBookRequest.getCategory());
        book.setPrice(createBookRequest.getPrice());
        book.setPublishedYear(createBookRequest.getPublishedYear());
        book.setDescription(createBookRequest.getDescription());
        book.setBookAuthors(authors);
        Book createdBook = bookRepository.save(book);

        return createdBook.getId();
    }

    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException(("Book is not found!"));
        }
        bookRepository.deleteById(id);
    }
}
