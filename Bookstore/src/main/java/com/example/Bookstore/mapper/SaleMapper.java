package com.example.Bookstore.mapper;

import com.example.Bookstore.dto.SaleDTO;
import com.example.Bookstore.model.Sale;

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

}
