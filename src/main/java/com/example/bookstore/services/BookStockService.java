package com.example.bookstore.services;

import com.example.bookstore.api.model.request.CreateBookStockRequest;
import com.example.bookstore.api.model.request.UpdateBookStockRequest;
import com.example.bookstore.api.model.response.BookStockResponse;
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.exceptions.ValidationException;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.BookStock;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.BookStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ehtiram_Abdullayev on 2/8/2020
 * @project book-store
 */
@Service
@Transactional
public class BookStockService {

    private final BookStockRepository bookStockRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookStockService(BookStockRepository bookStockRepository, BookRepository bookRepository) {
        this.bookStockRepository = bookStockRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookStock> getBooksAtShelf() {
        return bookStockRepository.findAll();
    }


    public BookStock getBookInShelveInStoreByShelfId(int id) {
        return bookStockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not in the shelf!"));
    }

    public Integer addBookToTheShelfInStore(CreateBookStockRequest createBookStockRequest) {
        Book existingBook = bookRepository.findById(createBookStockRequest.getBookId())
                .orElseThrow(() -> new ValidationException("Book is not found!"));

        BookStock bookStock = new BookStock();
        bookStock.setBook(existingBook);
        bookStock.setIsbn(createBookStockRequest.getIsbn());

        BookStock savedBookInShelf = bookStockRepository.save(bookStock);
        return savedBookInShelf.getId();
    }
    public List<BookStock> getBookInShelveByBookId(int bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException("Book is not found!");
        }
        return bookStockRepository.getBookStocksByBookId(bookId);
    }

    public List<BookStock> getBookStocksByBookByName(String bookName) {
        List<BookStock> bookStocks = bookStockRepository.getBookStocksByBookNameContainingIgnoreCase(bookName);
        if (bookStocks.isEmpty()) {
            throw new NotFoundException("Searched book has not found!");
        }
        return bookStocks;
    }
    public BookStockResponseWithBookCountResponse getBookInShelveByBookIdWithAvailableBookCount(int bookId) {
        List<BookStock> bookStocks = getBookInShelveByBookId(bookId);
        return mapBookStockListToWrapperClass(bookStocks);
    }

    public BookStockResponseWithBookCountResponse getBookInShelveByBookNameWithAvailableBookCount(String bookName) {
        List<BookStock> bookStocks = getBookStocksByBookByName(bookName);
        return mapBookStockListToWrapperClass(bookStocks);
    }

    private BookStockResponseWithBookCountResponse mapBookStockListToWrapperClass(List<BookStock> bookStocks) {
        if (bookStocks.isEmpty()) {
            throw new NotFoundException("Book is not found in the shelf!");
        }

        List<BookStockResponse> bookStockResponseList = bookStocks.stream()
                .map(BookStockResponse::new)
                .collect(Collectors.toList());

        int numberOfBooks = bookStockResponseList.size();

        BookStockResponseWithBookCountResponse withBookCount =
                new BookStockResponseWithBookCountResponse(bookStockResponseList, numberOfBooks);
        return withBookCount;
    }

    public void updateBookInShelf(UpdateBookStockRequest request) {
        BookStock bookStock = bookStockRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Book is not found in shelf!"));

        bookStock.setIsbn(request.getIsbn());
        bookStockRepository.save(bookStock);
    }

    public void deleteBookInShelve(int id) {
        if (!bookStockRepository.existsById(id)) {
            throw new NotFoundException("Book is not found in shelf!");
        }
        bookStockRepository.deleteById(id);
    }
}
