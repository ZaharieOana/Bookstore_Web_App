package com.example.Bookstore.controller;

import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }


    @GetMapping("/getById")
    public ResponseEntity getUserById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByID(id));
    }

    @GetMapping("/getByEmail")
    public ResponseEntity getUserByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
    }

    @PostMapping("/save")
    public ResponseEntity saveNewUser(@RequestBody UserCreationDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @PutMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserDTO user){
        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
