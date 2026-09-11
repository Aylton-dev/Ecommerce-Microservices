package com.example.catalog_service.repository;

import com.example.catalog_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByActiveTrue();

    Optional<Product> findByIdAndActiveTrue(Long id);
}
