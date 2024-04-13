package com.example.Bookstore.service;

import com.example.Bookstore.dto.AuthDTO;
import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<UserDTO> findAll();
    UserDTO findUserByID(Long id);
    UserDTO findUserByEmail(String email) throws ApiExceptionResponse ;
    UserDTO saveUser(UserCreationDTO newUser);
    void deleteUser(UserDTO user);
    User login(AuthDTO dto) throws ApiExceptionResponse;
}
