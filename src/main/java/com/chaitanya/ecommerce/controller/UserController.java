package com.chaitanya.ecommerce.controller;

import com.chaitanya.ecommerce.model.User;
import com.chaitanya.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping public List<User> all() { return repo.findAll(); }
    @GetMapping("/{id}") public User get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User u) {
        if (repo.existsByEmailIgnoreCase(u.getEmail())) throw new RuntimeException("Email already registered");
        return repo.save(u);
    }
    @PutMapping("/{id}") public User update(@PathVariable Long id, @Valid @RequestBody User input) {
        User u = get(id);
        u.setName(input.getName()); u.setEmail(input.getEmail()); u.setPassword(input.getPassword());
        return repo.save(u);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repo.delete(get(id)); }
}
