package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Sale;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.BookRepository;
import com.example.Bookstore.repository.SaleRepository;
import com.example.Bookstore.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final BookRepository bookRepository;

    public SaleServiceImpl(SaleRepository saleRepository, BookRepository bookRepository) {
        this.saleRepository = saleRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale saveSale(Sale newSale) {
        return saleRepository.save(newSale);
    }

    @Override
    public Sale makeSale(List<Book> books, User user) throws Exception {
        for(Book b : books) {
            if (b.getStock() == 0) {
                throw new Exception("not enough books in stock");
            }
            else if(!b.isAvailable()){
                throw new Exception("book not available");
            }
        }
        for(Book b : books){
            b.setStock(b.getStock() - 1);
            bookRepository.save(b);
        }
        Sale sale = new Sale();
        sale.setBooks(books);
        sale.setUser(user);
        sale.setSum();
        sale.setDate(LocalDate.now());
        return saleRepository.save(sale);
    }
}
