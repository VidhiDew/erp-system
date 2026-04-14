package com.vidhi.erp_saas.service;

import com.vidhi.erp_saas.dto.AuthResponse;
import com.vidhi.erp_saas.dto.LoginRequest;
import com.vidhi.erp_saas.dto.RegisterRequest;
import com.vidhi.erp_saas.entity.Role;
import com.vidhi.erp_saas.entity.User;
import com.vidhi.erp_saas.repository.UserRepository;
import com.vidhi.erp_saas.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Set.of(Role.ADMIN));

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        List<String> roles = user.getRole()
                .stream()
                .map(Role::name)
                .toList();

        return new AuthResponse(
                token,
                user.getEmail(),
                roles
        );
    }
}