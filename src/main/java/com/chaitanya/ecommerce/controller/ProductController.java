package com.chaitanya.ecommerce.controller;

import com.chaitanya.ecommerce.model.Product;
import com.chaitanya.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @GetMapping public List<Product> all() { return service.all(); }
    @GetMapping("/{id}") public Product get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody Product p) { return service.create(p); }
    @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody Product p) { return service.update(id, p); }
    @PatchMapping("/{id}") public Product patch(@PathVariable Long id, @RequestBody Product input) {
        Product p = service.get(id);
        if (input.getName() != null) p.setName(input.getName());
        if (input.getDescription() != null) p.setDescription(input.getDescription());
        if (input.getPrice() != null) p.setPrice(input.getPrice());
        if (input.getStock() != null) p.setStock(input.getStock());
        if (input.getCategory() != null) p.setCategory(input.getCategory());
        return service.update(id, p);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
