package com.example.Bookstore.controller;

import com.example.Bookstore.constants.Currency;
import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/currency")
    public ResponseEntity getCurrency(){
        Currency curr = Currency.getInstance();
        return ResponseEntity.status(HttpStatus.OK).body(curr.getCurrency());
    }

    @GetMapping("/getAllAvailable")
    public ResponseEntity getAllAvailableBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllAvailable());
    }

    @GetMapping("/getById")
    public ResponseEntity getBookById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookByID(id));
    }

    @GetMapping("/getByTitle")
    public ResponseEntity getBookByTitle(@RequestParam String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookByTitle(title));
    }

    @GetMapping("/getByType")
    public ResponseEntity getByType(@RequestBody BookTypeDTO type){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBooksByType(type));
    }

    @PostMapping("/save")
    public ResponseEntity saveNewBook(@RequestBody BookDTO book){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.saveBook(book));
    }

    @PutMapping("/delete")
    public ResponseEntity deleteBook(@RequestBody BookDTO book){
        bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    @PutMapping("/addToStock")
    public ResponseEntity addBooksToStock(@RequestBody BookDTO book, @RequestParam int amount){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.addToStock(book, amount));
    }
}
