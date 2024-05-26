package com.example.Bookstore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BookDTO {
    @NotNull
    @Size(min=1, max=50, message = "the title must have between 1 and 50 letters")
    private String title;
    @NotNull
    private String author;
    @NotNull
    private boolean available;
    @NotNull
    private int stock;
    @NotNull
    private double price;
    @NotNull
    private String type;
}
