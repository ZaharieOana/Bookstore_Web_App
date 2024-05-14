package com.example.Bookstore.mapper;

import com.example.Bookstore.dto.UserCreationDTO;
import com.example.Bookstore.dto.UserDTO;
import com.example.Bookstore.model.User;

public class UserMapper {

    public static User toEntity(UserDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .name(dto.getName())
                .type(dto.getType())
                .age(dto.getAge())
                .active(dto.isActive())
                .newsletter(dto.isNewsletter())
                .build();
    }

    public static UserDTO toDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .name(user.getName())
                .type(user.getType())
                .age(user.getAge())
                .active(user.isActive())
                .newsletter(user.isNewsletter())
                .build();
    }

    public static User toCreationEntity(UserCreationDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .name(dto.getName())
                .type(dto.getType())
                .age(dto.getAge())
                .newsletter(false)
                .build();
    }

    public static UserCreationDTO toCreationDTO(User user){
        return UserCreationDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .type(user.getType())
                .age(user.getAge())
                .build();
    }

}
