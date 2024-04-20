package com.example.Bookstore.dto;

import com.example.Bookstore.constants.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SuccessfulLogInDTO {
    private Long id;
    private UserType role;
}
