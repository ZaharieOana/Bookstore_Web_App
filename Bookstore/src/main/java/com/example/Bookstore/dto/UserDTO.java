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
public class UserDTO {
    private String name;
    private String email;
    private String phone;
    private UserType type;
    private int age;
    private boolean active;
    private boolean connected;
    private boolean newsletter;
}
