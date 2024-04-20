package com.example.Bookstore.controller;

import com.example.Bookstore.dto.SaleCreationDTO;
import com.example.Bookstore.dto.SaleDTO;
import com.example.Bookstore.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@CrossOrigin
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity saveNewSale(@RequestBody SaleDTO sale){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.saveSale(sale));
    }

    @PostMapping("/make")
    public ResponseEntity makeSale(@RequestBody SaleCreationDTO sale) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.makeSale(sale));
    }
}
