package com.example.Bookstore.dto;

import com.example.Bookstore.constants.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuccessfulLogInDTO {
    private Long id;
    private UserType role;
}
