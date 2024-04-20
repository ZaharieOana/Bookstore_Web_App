package com.example.Bookstore.controller;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.functionalities.EmailService;
import com.example.Bookstore.service.UserService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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

    @GetMapping("/getSubscription")
    public ResponseEntity getSubscription(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isUserSubscribed(email));
    }

    @PostMapping("/save")
    public ResponseEntity saveNewUser(@RequestBody UserCreationDTO user) throws ApiExceptionResponse {
        user.setType(UserType.CLIENT);
        String body = "User "+ user.getEmail() + " has created a new account at " + LocalDateTime.now();
        emailService.sendEmail("zaharieoanadenisa@gmail.com", "Sing Up Confirmation", body);
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @PutMapping("/subscribe")
    public ResponseEntity updateSubscription(@RequestParam String email, @RequestParam boolean ok) {
        userService.setSubscribe(email, ok);
        return ResponseEntity.status(HttpStatus.OK).body("Subscription updated");
    }

    @PutMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserDTO user){
        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
