package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.Sale;
import com.example.Bookstore.repository.SaleRepository;
import com.example.Bookstore.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale saveSale(Sale newSale) {
        return saleRepository.save(newSale);
    }
}
