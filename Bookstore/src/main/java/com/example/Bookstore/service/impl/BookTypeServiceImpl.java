package com.example.Bookstore.service.impl;

import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.mapper.BookTypeMapper;
import com.example.Bookstore.model.BookType;
import com.example.Bookstore.repository.BookTypeRepository;
import com.example.Bookstore.service.BookTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<BookTypeDTO> findAll() {
        List<BookType> typeList = (List<BookType>) bookTypeRepository.findAll();
        List<BookTypeDTO> types = new ArrayList<>();
        for(BookType t : typeList){
            types.add(BookTypeMapper.toDTO(t));
        }
        return types;
    }

    @Override
    public BookTypeDTO findBookTypeByName(String name) {
        BookType type = bookTypeRepository.findFirstByName(name);
        return BookTypeMapper.toDTO(type);
    }

    @Override
    public BookTypeDTO saveBookType(BookTypeDTO newBookType) {
        return BookTypeMapper.toDTO(bookTypeRepository.save(BookTypeMapper.toEntity(newBookType)));
    }
}
