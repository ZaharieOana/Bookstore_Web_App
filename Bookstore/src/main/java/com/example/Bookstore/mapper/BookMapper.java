package com.example.Bookstore.mapper;

import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookType;

public class BookMapper {
    public static Book toEntity(BookDTO dto){
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .available(dto.isAvailable())
                .stock(dto.getStock())
                .price(dto.getPrice())
                .type(new BookType(dto.getType()))
                .build();
    }

    public static BookDTO toDTO(Book book){
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .available(book.isAvailable())
                .stock(book.getStock())
                .price(book.getPrice())
                .type(book.getType().getName())
                .build();
    }
}
