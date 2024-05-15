package com.example.Bookstore.model;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.validator.UserTypeSubset;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@NotNull
    //@Size(min = 3, max = 50, message = "Size must be between {min} and {max}")
    private String name;
    //@NotNull
    //@Size(min = 8, max = 50, message = "Size must be between {min} and {max}")
    private String password;
    //@NotNull
    //@Email
    private String email;
    //@NotNull
    //@Pattern(regexp = "[0-9]{10}", message = "Phone number invalid")
    private String phone;
    //@UserTypeSubset(anyOf = {UserType.CLIENT, UserType.ADMIN})
    private UserType type;
    //@NotNull
    //@Min(value = 16, message = "You need to be at least 16")
    private int age;
    private boolean active;
    private boolean newsletter;
    private boolean connected;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone number='" + phone + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", active=" + active +
                '}';
    }
}
