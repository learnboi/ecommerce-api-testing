package com.chaitanya.ecommerce.config;

import com.chaitanya.ecommerce.model.Product;
import com.chaitanya.ecommerce.model.User;
import com.chaitanya.ecommerce.repository.ProductRepository;
import com.chaitanya.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(UserRepository users, ProductRepository products) {
        return args -> {
            users.save(new User("Test User", "test@example.com", "Password@123"));
            users.save(new User("Admin User", "admin@example.com", "Admin@123"));
            products.save(new Product("Wireless Mouse", "2.4GHz wireless mouse", new BigDecimal("799.00"), 25, "Electronics"));
            products.save(new Product("Mechanical Keyboard", "RGB mechanical keyboard", new BigDecimal("2499.00"), 10, "Electronics"));
            products.save(new Product("USB-C Cable", "Fast charging cable", new BigDecimal("399.00"), 50, "Accessories"));
        };
    }
}
