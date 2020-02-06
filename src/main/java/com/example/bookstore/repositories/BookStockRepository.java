package com.example.bookstore.repositories;

import com.example.bookstore.models.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Integer> {
    List<BookStock> getBookStocksByBookId(Integer bookId);
    List<BookStock> getBookStocksByBookNameContainingIgnoreCase(String bookName);
}
