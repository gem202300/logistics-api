package com.ivan.logisticsapi.service;

import com.ivan.logisticsapi.dto.AuthResponse;
import com.ivan.logisticsapi.dto.LoginRequest;
import com.ivan.logisticsapi.dto.RegisterRequest;
import com.ivan.logisticsapi.enums.Role;
import com.ivan.logisticsapi.model.User;
import com.ivan.logisticsapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private UserRepository userRepository;
    private final JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already exists");
        }
        String hashed = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getEmail(), hashed, Role.USER);
        userRepository.save(newUser);
    }
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

}
