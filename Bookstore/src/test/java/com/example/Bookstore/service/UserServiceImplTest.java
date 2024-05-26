package com.example.Bookstore.service;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.UserRepository;
import com.example.Bookstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        initMocks(this);
        userService = new UserServiceImpl();
    }

    @Test
    public void createUser() {
        User user = new User(1L, "Test User", "pass", "test@t.t", UserType.CLIENT, 21, true);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void givenExistingUser_whenDeleteUser() {
        User user = new User(1L, "Test User", "pass", "test@t.t", UserType.CLIENT, 21, true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(user);
        User result = userService.findUserByID(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertFalse(result.isActive());
    }

    @Test
    public void givenExistingUser_whenFindUserByEmail() throws Exception {
        String email = "test@t.t";
        User user = new User(1L, "Test User", "pass", "test@t.t", UserType.CLIENT, 21, true);
        when(userRepository.findFirstByEmail(email)).thenReturn(user);

        User result = userService.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findFirstByEmail(email);
        verify(userRepository, times(0)).findAll();
    }

}
