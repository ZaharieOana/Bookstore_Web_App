package com.example.Bookstore.mapper;

import com.example.Bookstore.dto.SaleDTO;
import com.example.Bookstore.dto.SaleExportDTO;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleMapper {

    public static Sale toEntity(SaleDTO dto){
        return Sale.builder()
                .sum(dto.getSum())
                .date(dto.getDate())
                .user(dto.getUser())
                .books(dto.getBooks())
                .build();
    }

    public static SaleDTO toDTO(Sale sale){
        return SaleDTO.builder()
                .sum(sale.getSum())
                .date(sale.getDate())
                .user(sale.getUser())
                .books(sale.getBooks())
                .build();
    }

    public static SaleExportDTO toExportDTO(Sale sale){
        List<String> books = new ArrayList<>();
        for(Book b : sale.getBooks())
            books.add(b.getTitle());
        return SaleExportDTO.builder()
                .sum(sale.getSum())
                .date(sale.getDate())
                .user(sale.getUser().getEmail())
                .books(books.toString())
                .build();
    }

}
