package com.example.bookstore.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */

@Entity
@Table(name = "book_stock")
public class BookStock extends BasicEntity {

    @ManyToOne
    private Book book;

    @Column
    private String isbn;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime bookAddedToShelfAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime bookAtShelfUpdatedAt;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getBookAddedToShelfAt() {
        return bookAddedToShelfAt;
    }

    public LocalDateTime getBookAtShelfUpdatedAt() {
        return bookAtShelfUpdatedAt;
    }
}
