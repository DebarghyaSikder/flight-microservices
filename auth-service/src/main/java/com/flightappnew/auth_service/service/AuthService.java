package com.flightappnew.auth_service.service;

import com.flightappnew.auth_service.dto.AuthRequest;
import com.flightappnew.auth_service.dto.AuthResponse;
import com.flightappnew.auth_service.dto.RegisterRequest;
import com.flightappnew.auth_service.dto.ValidateTokenResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<AuthResponse> login(AuthRequest request);

    Mono<AuthResponse> register(RegisterRequest request);

    Mono<ValidateTokenResponse> validateToken(String token);
}
