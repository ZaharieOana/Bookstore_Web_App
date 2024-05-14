package com.example.Bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 50, message = "Size must be between {min} and {max}")
    private String password;
}
