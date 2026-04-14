package com.vidhi.erp_saas.controller;

import com.vidhi.erp_saas.dto.AuthResponse;
import com.vidhi.erp_saas.dto.LoginRequest;
import com.vidhi.erp_saas.dto.RegisterRequest;
import com.vidhi.erp_saas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/test")
    public String test() {
        return "API is secured!";
    }
}