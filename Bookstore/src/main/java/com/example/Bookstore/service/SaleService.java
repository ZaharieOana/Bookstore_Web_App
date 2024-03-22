package com.example.Bookstore.service;

import com.example.Bookstore.model.Sale;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SaleService {
    List<Sale> findAll();
    Sale saveSale(Sale newSale);
}
