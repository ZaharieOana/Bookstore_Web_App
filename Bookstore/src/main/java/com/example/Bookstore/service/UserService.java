package com.example.Bookstore.service;

import com.example.Bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> findAll();
    User findUserByID(Long id);
    User findUserByEmail(String email) throws Exception;
    User saveUser(User newUser);
    void deleteUser(User user);
}
