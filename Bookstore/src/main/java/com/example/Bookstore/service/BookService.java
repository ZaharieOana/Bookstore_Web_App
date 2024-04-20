package com.example.Bookstore.service;

import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.dto.BookTypeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<BookDTO> findAll();
    List<BookDTO> findAllAvailable();
    BookDTO findBookByID(Long id);
    BookDTO findBookByTitle(String title);
    List<BookDTO> findBooksByType(BookTypeDTO type);
    BookDTO saveBook(BookDTO newBook);
    void deleteBook(BookDTO book);
    BookDTO addToStock(BookDTO book, int amount);
}
