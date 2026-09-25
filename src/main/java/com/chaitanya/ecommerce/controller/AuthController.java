package com.chaitanya.ecommerce.controller;

import com.chaitanya.ecommerce.model.User;
import com.chaitanya.ecommerce.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}
    public record RefreshRequest(@NotBlank String refreshToken) {}

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Invalid email or password");
        }

        return Map.of(
                "message", "Login successful",
                "userId", user.getId(),
                "accessToken", "access-" + UUID.randomUUID(),
                "refreshToken", "refresh-" + UUID.randomUUID()
        );
    }

    @PostMapping("/logout")
    public Map<String, String> logout() {
        return Map.of("message", "Logout successful");
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody RefreshRequest request) {
        if (!request.refreshToken().startsWith("refresh-")) {
            throw new RuntimeException("Invalid refresh token");
        }
        return Map.of(
                "accessToken", "access-" + UUID.randomUUID(),
                "message", "Token refreshed"
        );
    }
}
