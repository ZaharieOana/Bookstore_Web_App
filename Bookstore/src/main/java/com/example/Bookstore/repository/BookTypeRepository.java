package com.example.Bookstore.repository;

import com.example.Bookstore.model.BookType;
import com.example.Bookstore.service.BookTypeService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends CrudRepository<BookType, Long> {
    BookType findFirstByName(String name);
}
