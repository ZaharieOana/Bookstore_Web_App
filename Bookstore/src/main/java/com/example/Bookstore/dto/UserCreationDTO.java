package com.example.Bookstore.dto;

import com.example.Bookstore.constants.UserType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserCreationDTO {
    @NotNull
    @Size(min = 3, max = 50, message = "Size must be between {min} and {max}")
    private String name;
    @NotNull
    @Size(min = 8, max = 50, message = "Size must be between {min} and {max}")
    private String password;
    @NotNull 
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Phone number invalid")
    private String phone;
    private UserType type;
    @NotNull
    @Min(value = 16, message = "You need to be at least 16")
    private int age;
}
