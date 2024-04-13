package com.example.Bookstore.dto;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class SaleDTO {
    private int sum;
    private LocalDate date;
    private User user;
    private List<Book> books;
}
