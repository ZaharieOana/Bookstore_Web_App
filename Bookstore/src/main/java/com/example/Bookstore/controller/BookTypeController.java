package com.example.Bookstore.controller;

import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.service.BookTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class BookTypeController {
    @Autowired
    private BookTypeService bookTypeService;

    @Operation(summary = "Get a list of all types of books")
    @GetMapping("/getAll")
    public ResponseEntity getAllTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.findAll());
    }

    @Operation(summary = "Get a certain type by name")
    @GetMapping("/getByName")
    public ResponseEntity getTypeByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.findBookTypeByName(name));
    }

    @Operation(summary = "Save a book type")
    @PostMapping("/save")
    public ResponseEntity saveNewType(@RequestBody BookTypeDTO type){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.saveBookType(type));
    }
}
