package com.example.Bookstore.service.impl;

import com.example.Bookstore.dto.*;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.mapper.UserMapper;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.UserRepository;
import com.example.Bookstore.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDTO> users = new ArrayList<>();
        for(User u : userList){
            users.add(UserMapper.toDTO(u));
        }
        return users;
    }

    @Override
    public UserDTO findUserByID(Long id) {
        return UserMapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDTO findUserByEmail(String email) throws ApiExceptionResponse {
        User user =  userRepository.findFirstByEmail(email);
        if(user == null){
            throw ApiExceptionResponse.builder()
                    .errors(Collections.singletonList("No user with email " + email))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO saveUser(UserCreationDTO newUser) throws ApiExceptionResponse {
        User u = userRepository.findFirstByEmail(newUser.getEmail());
        if(u != null){
            throw ApiExceptionResponse.builder()
                    .errors(Collections.singletonList("Already exists user with email "))
                    .message("User exists")
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .build();
        }
        User user = UserMapper.toCreationEntity(newUser);
        user.setActive(true);
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(UserDTO user) {
        User u = userRepository.findFirstByEmail(user.getEmail());
        if(u != null){
            u.setActive(false);
            userRepository.save(u);
        }
    }

    @Override
    public SuccessfulLogInDTO login(AuthDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(user == null || !user.isActive()){
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Email or password invalid");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return SuccessfulLogInDTO.builder().role(user.getType()).id(user.getId()).build();
    }

    @Override
    public boolean isUserSubscribed(String email) {
        User user = userRepository.findFirstByEmail(email);
        return user.isNewsletter();
    }

    @Override
    public void setSubscribe(String email, boolean ok) {
        User user = userRepository.findFirstByEmail(email);
        user.setNewsletter(ok);
        userRepository.save(user);
    }
}
