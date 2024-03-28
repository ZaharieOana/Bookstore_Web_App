package com.example.Bookstore.service;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<Book> findAll();
    List<Book> findAllAvailable();
    Book findBookByID(Long id);
    Book findBookByTitle(String title);
    List<Book> findBooksByType(BookType type);
    Book saveBook(Book newBook);
    void deleteBook(Book book);
    Book addToStock(Book book, int amount);
}
