package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.BookType;
import com.example.Bookstore.repository.BookTypeRepository;
import com.example.Bookstore.service.BookTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeRepository bookTypeRepository;
    @Override
    public List<BookType> findAll() {
        return (List<BookType>) bookTypeRepository.findAll();
    }

    @Override
    public BookType saveBookType(BookType newBookType) {
        return bookTypeRepository.save(newBookType);
    }
}
