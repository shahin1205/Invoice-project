package com.example.product.repository;


import com.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    boolean existsByProductCode(String productCode);

    Optional<Product> findByProductCode(String productCode);

    List<Product> findByProductNameContainingIgnoreCase(String productName);
}
