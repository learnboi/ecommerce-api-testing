package com.chaitanya.ecommerce.service;

import com.chaitanya.ecommerce.model.Product;
import com.chaitanya.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> all() { return repo.findAll(); }
    public Product get(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found")); }
    public Product create(Product p) {
        if (repo.existsByNameIgnoreCase(p.getName())) throw new RuntimeException("Product with this name already exists");
        return repo.save(p);
    }
    public Product update(Long id, Product input) {
        Product p = get(id);
        p.setName(input.getName()); p.setDescription(input.getDescription());
        p.setPrice(input.getPrice()); p.setStock(input.getStock()); p.setCategory(input.getCategory());
        return repo.save(p);
    }
    public void delete(Long id) { repo.delete(get(id)); }
}
