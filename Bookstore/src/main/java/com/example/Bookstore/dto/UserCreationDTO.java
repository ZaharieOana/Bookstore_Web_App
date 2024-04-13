package com.example.Bookstore.dto;

import com.example.Bookstore.constants.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserCreationDTO {
    private String name;
    private String password;
    private String email;
    private UserType type;
    private int age;
}
