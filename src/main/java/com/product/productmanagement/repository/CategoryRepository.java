package com.product.productmanagement.repository;

import com.product.productmanagement.model.Category;
import com.product.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
