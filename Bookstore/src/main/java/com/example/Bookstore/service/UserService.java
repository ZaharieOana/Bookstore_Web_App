package com.example.Bookstore.service;

import com.example.Bookstore.dto.AuthDTO;
import com.example.Bookstore.dto.SuccessfulLogInDTO;
import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<UserDTO> findAll();
    UserDTO findUserByID(Long id);
    UserDTO findUserByEmail(String email) throws ApiExceptionResponse ;
    UserDTO saveUser(UserCreationDTO newUser) throws ApiExceptionResponse;
    void deleteUser(UserDTO user);
    SuccessfulLogInDTO login(AuthDTO dto) throws ApiExceptionResponse;
    void logout(String email);
    boolean isUserSubscribed(String email);
    void setSubscribe(String email, boolean ok);
    UserDTO changePassword(String email, String password);
}
