package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.AuthResponse;
import com.ivan.logisticsapi.dto.LoginRequest;
import com.ivan.logisticsapi.dto.RegisterRequest;
import com.ivan.logisticsapi.enums.Role;
import com.ivan.logisticsapi.model.User;
import com.ivan.logisticsapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_shouldSaveUser_whenEmailNotExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("new@test.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail("new@test.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashed");

        authService.register(request);

        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_shouldThrow_whenEmailExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("exists@test.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail("exists@test.com")).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void login_shouldReturnToken_whenCredentialsValid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("user@test.com");
        request.setPassword("password123");

        User user = new User("user@test.com", "hashed", Role.USER);

        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "hashed")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("fake-jwt-token");

        AuthResponse response = authService.login(request);

        assertEquals("fake-jwt-token", response.getToken());
    }

    @Test
    void login_shouldThrow_whenPasswordInvalid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("user@test.com");
        request.setPassword("wrong");

        User user = new User("user@test.com", "hashed", Role.USER);

        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }
}