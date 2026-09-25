package com.chaitanya.ecommerce.controller;

import com.chaitanya.ecommerce.model.Product;
import com.chaitanya.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product input) {
        Product product = getById(id);
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setStock(input.getStock());
        product.setCategory(input.getCategory());
        return repository.save(product);
    }

    @PatchMapping("/{id}")
    public Product patch(@PathVariable Long id, @RequestBody Product input) {
        Product product = getById(id);
        if (input.getName() != null) product.setName(input.getName());
        if (input.getDescription() != null) product.setDescription(input.getDescription());
        if (input.getPrice() != null) product.setPrice(input.getPrice());
        if (input.getStock() != null) product.setStock(input.getStock());
        if (input.getCategory() != null) product.setCategory(input.getCategory());
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found: " + id);
        }
        repository.deleteById(id);
    }
}
