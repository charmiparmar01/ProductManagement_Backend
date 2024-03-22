package com.product.productmanagement.repository;

import com.product.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByCategoryId(Integer catId);
}
