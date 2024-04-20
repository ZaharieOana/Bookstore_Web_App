package com.example.Bookstore.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class SaleCreationDTO {
    private List<BookDTO> books;
    private Long userId;
}
