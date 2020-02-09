package com.example.bookstore.utils.builder;

import com.example.bookstore.models.Book;

/**
 * @author Ehtiram_Abdullayev on 2/9/2020
 * @project book-store
 */
public class BookTestDataBuilder {
    public static Book createBook() {
        return createBook(null);
    }

    public static Book createBook(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setName("Name");
        book.setCategory("Action and Adventure");
        book.setDescription("Desc");
        book.setPublishedYear(1995);

        return book;
    }
}
