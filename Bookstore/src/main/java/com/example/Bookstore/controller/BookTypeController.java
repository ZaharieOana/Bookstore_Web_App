package com.example.Bookstore.controller;

import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.service.BookTypeService;
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

    @GetMapping("/getAll")
    public ResponseEntity getAllTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.findAll());
    }

    @GetMapping("/getByName")
    public ResponseEntity getTypeByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.findBookTypeByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity saveNewType(@RequestBody BookTypeDTO type){
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeService.saveBookType(type));
    }
}
