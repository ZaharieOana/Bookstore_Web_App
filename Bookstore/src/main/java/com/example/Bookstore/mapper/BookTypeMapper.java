package com.example.Bookstore.mapper;

import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.model.BookType;

public class BookTypeMapper {
    public static BookType toEntity(BookTypeDTO dto){
        BookType type = new BookType();
        type.setName(dto.getName());
        return type;
    }

    public static BookTypeDTO toDTO(BookType type){
        BookTypeDTO dto = new BookTypeDTO();
        dto.setName(type.getName());
        return dto;
    }
}
