package com.example.Bookstore.service;

import com.example.Bookstore.dto.BookTypeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookTypeService {
    List<BookTypeDTO> findAll();
    BookTypeDTO findBookTypeByName(String name);
    BookTypeDTO saveBookType(BookTypeDTO newBookType);
}
