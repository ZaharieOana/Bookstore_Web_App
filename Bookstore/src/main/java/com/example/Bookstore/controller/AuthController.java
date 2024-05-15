package com.example.Bookstore.controller;

import com.example.Bookstore.dto.AuthDTO;
import com.example.Bookstore.exceptions.ApiExceptionResponse;
import com.example.Bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Login with email and password")
    @PostMapping
    public ResponseEntity login(@RequestBody AuthDTO auth) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(auth));
    }



}
