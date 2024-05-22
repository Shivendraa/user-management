package com.shivendra.usermanagement.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.shivendra.usermanagement.model.User;
import com.shivendra.usermanagement.repository.UserRepository;
import com.shivendra.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUser_Success() {
        // Arrange
        String username = "shivendrak";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        User result = userService.loginUser(username, password);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test
    void testLoginUser_InvalidPassword() {
        // Arrange
        String username = "shivendrak";
        String password = "password";
        String wrongPassword = "wrongpassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        User result = userService.loginUser(username, wrongPassword);

        // Assert
        assertNull(result);
    }

    @Test
    void testLoginUser_UserNotFound() {
        // Arrange
        String username = "shivendra";
        String password = "password";

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // Act
        User result = userService.loginUser(username, password);

        // Assert
        assertNull(result);
    }
}
