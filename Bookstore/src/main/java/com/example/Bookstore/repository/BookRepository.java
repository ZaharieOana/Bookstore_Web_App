package com.example.Bookstore.repository;

import com.example.Bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByAvailable(boolean availability);
    List<Book> findAllByType(String type);
    Book findFirstByTitle(String title);

}
