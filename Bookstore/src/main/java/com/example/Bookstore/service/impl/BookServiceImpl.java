package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repository.BookRepository;
import com.example.Bookstore.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public List<Book> findAllAvailable() {
        return (List<Book>) bookRepository.findAllByAvailable(true);
    }

    @Override
    public Book findBookByID(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findFirstByTitle(title);
    }

//    @Override
//    public List<Book> findBooksByType(String type) {
//        return (List<Book>) bookRepository.findAllByType(type);
//    }

    @Override
    public Book saveBook(Book newBook) {
        newBook.setAvailable(true);
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Book book) {
        boolean exists = bookRepository.findById(book.getId()).isPresent();
        if(exists){
            book.setAvailable(false);
            bookRepository.save(book);
        }
    }

    @Override
    public Book addToStock(Book book, int amount) {
        book.setStock(book.getStock() + amount);
        return bookRepository.save(book);
    }
}
