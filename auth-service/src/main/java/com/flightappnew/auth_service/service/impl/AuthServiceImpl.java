package com.flightappnew.auth_service.service.impl;

import com.flightappnew.auth_service.dto.AuthRequest;
import com.flightappnew.auth_service.dto.AuthResponse;
import com.flightappnew.auth_service.dto.ChangePasswordRequest;
import com.flightappnew.auth_service.dto.RegisterRequest;
import com.flightappnew.auth_service.dto.ValidateTokenResponse;
import com.flightappnew.auth_service.entity.User;
import com.flightappnew.auth_service.repository.UserRepository;
import com.flightappnew.auth_service.service.AuthService;
import com.flightappnew.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<AuthResponse> login(AuthRequest request) {
        log.info("Login attempt for username: {}", request.getUsername());

        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .filter(User::getEnabled)
                .map(user -> {
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("email", user.getEmail());
                    claims.put("role", user.getRole());

                    String token = jwtUtil.generateToken(user.getUsername(), claims);

                    log.info("Login successful for user: {}", user.getUsername());

                    return AuthResponse.builder()
                            .token(token)
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .message("Login successful")
                            .build();
                })
                .switchIfEmpty(Mono.just(AuthResponse.builder()
                        .message("Invalid username or password")
                        .build()));
    }

    @Override
    public Mono<AuthResponse> register(RegisterRequest request) {
        log.info("Registration attempt for username: {}", request.getUsername());

        return userRepository.existsByUsername(request.getUsername())
                .flatMap(usernameExists -> {
                    if (Boolean.TRUE.equals(usernameExists)) {
                        return Mono.just(AuthResponse.builder()
                                .message("Username already exists")
                                .build());
                    }

                    return userRepository.existsByEmail(request.getEmail())
                            .flatMap(emailExists -> {
                                if (Boolean.TRUE.equals(emailExists)) {
                                    return Mono.just(AuthResponse.builder()
                                            .message("Email already exists")
                                            .build());
                                }

                                User user = User.builder()
                                        .username(request.getUsername())
                                        .password(passwordEncoder.encode(request.getPassword()))
                                        .email(request.getEmail())
                                        .firstName(request.getFirstName())
                                        .lastName(request.getLastName())
                                        .role("USER")
                                        .enabled(true)
                                        .createdAt(LocalDateTime.now())
                                        .updatedAt(LocalDateTime.now())
                                        .build();

                                return userRepository.save(user)
                                        .map(savedUser -> {
                                            Map<String, Object> claims = new HashMap<>();
                                            claims.put("email", savedUser.getEmail());
                                            claims.put("role", savedUser.getRole());

                                            String token = jwtUtil.generateToken(savedUser.getUsername(), claims);

                                            log.info("Registration successful for user: {}", savedUser.getUsername());

                                            return AuthResponse.builder()
                                                    .token(token)
                                                    .username(savedUser.getUsername())
                                                    .email(savedUser.getEmail())
                                                    .role(savedUser.getRole())
                                                    .message("Registration successful")
                                                    .build();
                                        });
                            });
                });
    }
    
    @Override
    public void changePassword(String authHeader, ChangePasswordRequest req) {

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public Mono<ValidateTokenResponse> validateToken(String token) {
        try {
            if (!jwtUtil.validateToken(token)) {
                return Mono.just(ValidateTokenResponse.builder()
                        .valid(false)
                        .message("Invalid or expired token")
                        .build());
            }

            String username = jwtUtil.extractUsername(token);

            return userRepository.findByUsername(username)
                    .filter(User::getEnabled)
                    .map(user -> ValidateTokenResponse.builder()
                            .valid(true)
                            .username(user.getUsername())
                            .message("Token is valid")
                            .build())
                    .switchIfEmpty(Mono.just(ValidateTokenResponse.builder()
                            .valid(false)
                            .message("User not found or disabled")
                            .build()));

        } catch (Exception e) {
            log.error("Token validation error", e);
            return Mono.just(ValidateTokenResponse.builder()
                    .valid(false)
                    .message("Token validation failed")
                    .build());
        }
    }
}
