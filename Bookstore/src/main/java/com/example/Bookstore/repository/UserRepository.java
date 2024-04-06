package com.example.Bookstore.repository;

import com.example.Bookstore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByEmail(String email);
}
