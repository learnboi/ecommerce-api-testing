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
    private final UserRepository repo;
    public AuthController(UserRepository repo) { this.repo = repo; }

    record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest req) {
        User u = repo.findByEmail(req.email()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!u.getPassword().equals(req.password())) throw new RuntimeException("Invalid credentials");
        return Map.of("accessToken", "access-" + UUID.randomUUID(), "refreshToken", "refresh-" + UUID.randomUUID(),
                "tokenType", "Bearer", "userId", u.getId());
    }

    @PostMapping("/refresh")
    public Map<String, Object> refresh(@RequestBody Map<String, String> body) {
        String token = body.get("refreshToken");
        if (token == null || !token.startsWith("refresh-")) throw new RuntimeException("Invalid refresh token");
        return Map.of("accessToken", "access-" + UUID.randomUUID(), "tokenType", "Bearer");
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {}
}
