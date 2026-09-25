package com.chaitanya.ecommerce.repository;

import com.chaitanya.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
