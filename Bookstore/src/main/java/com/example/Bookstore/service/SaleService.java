package com.example.Bookstore.service;

import com.example.Bookstore.dto.SaleCreationDTO;
import com.example.Bookstore.dto.SaleDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SaleService {
    List<SaleDTO> findAll();
    SaleDTO saveSale(SaleDTO newSale);
    //SaleDTO makeSale(SaleCreationDTO dto) throws Exception;
    SaleDTO makeSale(String email) throws Exception;
    String exportSales();
}
