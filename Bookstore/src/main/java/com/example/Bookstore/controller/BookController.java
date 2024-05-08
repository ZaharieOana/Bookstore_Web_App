package com.example.Bookstore.controller;

import com.example.Bookstore.constants.Currency;
import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get a list of all books")
    @GetMapping("/getAll")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @Operation(summary = "Get a map with all currencies and their value")
    @GetMapping("/currency")
    public ResponseEntity getCurrency(){
        Currency curr = Currency.getInstance();
        return ResponseEntity.status(HttpStatus.OK).body(curr.getCurrency());
    }

    @Operation(summary = "Get a list of all available books")
    @GetMapping("/getAllAvailable")
    public ResponseEntity getAllAvailableBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllAvailable());
    }

    @Operation(summary = "Get a book by id")
    @GetMapping("/getById")
    public ResponseEntity getBookById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookByID(id));
    }

    @Operation(summary = "Get a book by title")
    @GetMapping("/getByTitle")
    public ResponseEntity getBookByTitle(@RequestParam String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookByTitle(title));
    }

    @Operation(summary = "Get a list of all books of a certain type")
    @GetMapping("/getByType")
    public ResponseEntity getByType(@RequestBody BookTypeDTO type){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBooksByType(type));
    }

    @Operation(summary = "Save a book")
    @PostMapping("/save")
    public ResponseEntity saveNewBook(@RequestBody BookDTO book){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.saveBook(book));
    }

    @Operation(summary = "Delete a book (make it unavailable)")
    @PutMapping("/delete")
    public ResponseEntity deleteBook(@RequestBody BookDTO book){
        bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    @Operation(summary = "Add existing books to stock")
    @PutMapping("/addToStock")
    public ResponseEntity addBooksToStock(@RequestBody BookDTO book, @RequestParam int amount){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.addToStock(book, amount));
    }
}
