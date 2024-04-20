package com.example.Bookstore.service.impl;

import com.example.Bookstore.dto.BookDTO;
import com.example.Bookstore.dto.BookTypeDTO;
import com.example.Bookstore.mapper.BookMapper;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookType;
import com.example.Bookstore.repository.BookRepository;
import com.example.Bookstore.repository.BookTypeRepository;
import com.example.Bookstore.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookTypeRepository bookTypeRepository;


    @Override
    public List<BookDTO> findAll() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<BookDTO> books = new ArrayList<>();
        for(Book b : bookList){
            books.add(BookMapper.toDTO(b));
        }
        return books;
    }

    @Override
    public List<BookDTO> findAllAvailable() {
        List<Book> bookList = bookRepository.findAllByAvailable(true);
        List<BookDTO> books = new ArrayList<>();
        for(Book b : bookList){
            books.add(BookMapper.toDTO(b));
        }
        return books;
    }

    @Override
    public BookDTO findBookByID(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return BookMapper.toDTO(book);
    }

    @Override
    public BookDTO findBookByTitle(String title) {
        Book book = bookRepository.findFirstByTitle(title);
        return BookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> findBooksByType(BookTypeDTO type) {
        BookType t = bookTypeRepository.findFirstByName(type.getName());
        List<Book> bookList = bookRepository.findAllByType(t);
        List<BookDTO> books = new ArrayList<>();
        for(Book b : bookList){
            books.add(BookMapper.toDTO(b));
        }
        return books;
    }

    @Override
    public BookDTO saveBook(BookDTO newBook) {
        Book book = BookMapper.toEntity(newBook);
        BookType type = bookTypeRepository.findFirstByName(newBook.getType());
        book.setType(type);
        book.setAvailable(true);
        return BookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(BookDTO book) {
        Book b = bookRepository.findFirstByTitle(book.getTitle());
        if(b != null){
            b.setAvailable(false);
            bookRepository.save(b);
        }
    }

    @Override
    public BookDTO addToStock(BookDTO book, int amount) {
        Book b = bookRepository.findFirstByTitle(book.getTitle());
        b.setStock(b.getStock() + amount);
        return BookMapper.toDTO(bookRepository.save(b));
    }
}
