package com.example.Bookstore.model;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.validator.UserTypeSubset;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

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
    private String name;
    private String password;
    private String email;
    private String phone;
    //@UserTypeSubset(anyOf = {UserType.CLIENT, UserType.ADMIN})
    private UserType type;
    private int age;
    private boolean active;
    private boolean newsletter;
    private boolean connected;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> cart;

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
