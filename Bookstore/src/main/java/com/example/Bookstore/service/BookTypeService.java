package com.example.Bookstore.service;

import com.example.Bookstore.model.BookType;
import com.example.Bookstore.repository.BookTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookTypeService {
    List<BookType> findAll();
    BookType saveBookType(BookType newBookType);
}
