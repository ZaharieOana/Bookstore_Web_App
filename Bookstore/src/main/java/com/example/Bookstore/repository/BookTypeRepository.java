package com.example.Bookstore.repository;

import com.example.Bookstore.model.BookType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends CrudRepository<BookType, Long> {
}
