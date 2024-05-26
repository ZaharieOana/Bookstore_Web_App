package com.example.Bookstore.controller;

import com.example.Bookstore.dto.SaleCreationDTO;
import com.example.Bookstore.dto.SaleDTO;
import com.example.Bookstore.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get a list of all sales")
    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @Operation(summary = "Save a sale")
    @PostMapping("/save")
    public ResponseEntity saveNewSale(@RequestBody SaleDTO sale){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.saveSale(sale));
    }

    @Operation(summary = "Make a sale")
    @PostMapping("/make")
    public ResponseEntity makeSale(@RequestBody String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.makeSale(email));
    }

    @GetMapping("/download")
    public ResponseEntity downloadSales(){
        return ResponseEntity.ok(saleService.exportSales());
    }

}
