package com.example.Bookstore.service.impl;

import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.dto.SaleCreationDTO;
import com.example.Bookstore.dto.SaleDTO;
import com.example.Bookstore.dto.SaleExportDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.functionalities.exporter.FileExporter;
import com.example.Bookstore.functionalities.exporter.XMLFileExporter;
import com.example.Bookstore.mapper.SaleMapper;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Sale;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.BookRepository;
import com.example.Bookstore.repository.SaleRepository;
import com.example.Bookstore.repository.UserRepository;
import com.example.Bookstore.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<SaleDTO> findAll() {
        List<Sale> saleList = (List<Sale>) saleRepository.findAll();
        List<SaleDTO> sales = new ArrayList<>();
        for(Sale s : saleList){
            sales.add(SaleMapper.toDTO(s));
        }
        return sales;
    }

    @Override
    public SaleDTO saveSale(SaleDTO newSale) {
        return SaleMapper.toDTO(saleRepository.save(SaleMapper.toEntity(newSale)));
    }

    @Override
    public SaleDTO makeSale(String email) throws Exception {
        ArrayList<String> errors = new ArrayList<>();
        User user = userRepository.findFirstByEmail(email);
        List<Book> books = user.getCart();
        for(Book b : books) {
            if(!b.isAvailable()){
            errors.add(b.getTitle() + "is not available");
            } else if (b.getStock() == 0) {
                errors.add(b.getTitle() + "is out of stock");
            }

        }
        if(errors.size() > 0){
            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Entity not available")
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .build();
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
        user.setCart(new ArrayList<>());
        userRepository.save(user);
        return SaleMapper.toDTO(saleRepository.save(sale));
    }

//    @Override
//    public SaleDTO makeSale(SaleCreationDTO dto) throws Exception {
//        ArrayList<String> errors = new ArrayList<>();
//        List<Book> books = new ArrayList<>();
//        for(BookDTO b : dto.getBooks())
//            books.add(bookRepository.findFirstByTitle(b.getTitle()));
//        for(Book b : books) {
//            if(!b.isAvailable()){
//            errors.add(b.getTitle() + "is not available");
//            } else if (b.getStock() == 0) {
//                errors.add(b.getTitle() + "is out of stock");
//            }
//
//        }
//        for(Book b : books){
//            b.setStock(b.getStock() - 1);
//            bookRepository.save(b);
//        }
//        if(errors.size() > 0){
//            throw ApiExceptionResponse.builder()
//                    .errors(errors)
//                    .message("Entity not available")
//                    .status(HttpStatus.EXPECTATION_FAILED)
//                    .build();
//        }
//        Sale sale = new Sale();
//        sale.setBooks(books);
//        sale.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
//        sale.setSum();
//        sale.setDate(LocalDate.now());
//        return SaleMapper.toDTO(saleRepository.save(sale));
//    }

    @Override
    public String exportSales() {
        FileExporter fileExporter = new XMLFileExporter();
        List<Sale> sales = (List<Sale>) saleRepository.findAll();
        List<SaleExportDTO> dtos = new ArrayList<>();
        for(Sale s :sales)
            dtos.add(SaleMapper.toExportDTO(s));
        return fileExporter.exportSales(dtos);
    }
}
