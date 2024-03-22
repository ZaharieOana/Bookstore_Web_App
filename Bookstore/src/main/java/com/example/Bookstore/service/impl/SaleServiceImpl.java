package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.Sale;
import com.example.Bookstore.repository.SaleRepository;
import com.example.Bookstore.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale saveSale(Sale newSale) {
        return saleRepository.save(newSale);
    }
}
