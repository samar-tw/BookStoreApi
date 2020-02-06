package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ehtiram_Abdullayev on 2/6/2020
 * @project book-store
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
