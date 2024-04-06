package com.example.Bookstore.service;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Sale;
import com.example.Bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SaleService {
    List<Sale> findAll();
    Sale saveSale(Sale newSale);
    Sale makeSale(List<Book> books, User user) throws Exception;
}
