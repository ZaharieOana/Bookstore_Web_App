package com.example.Bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BookDTO {
    private String title;
    private String author;
    private boolean available;
    private int stock;
    private double price;
    private String type;
}
