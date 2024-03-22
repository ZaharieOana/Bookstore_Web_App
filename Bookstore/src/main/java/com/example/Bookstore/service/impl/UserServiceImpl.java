package com.example.Bookstore.service.impl;

import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.UserRepository;
import com.example.Bookstore.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserByID(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user =  userRepository.findFirstByEmail(email);
        if(user == null){
            throw new Exception("No user with this email");
        }
        return user;
    }

    @Override
    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(User user) {
        boolean exists = userRepository.findById(user.getId()).isPresent();
        if(exists){
            user.setActive(false);
            userRepository.save(user);
        }
    }
}
