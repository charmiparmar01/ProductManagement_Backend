package com.product.productmanagement.controller;

import com.product.productmanagement.model.Category;
import com.product.productmanagement.model.Product;
import com.product.productmanagement.service.CategoryService;
import com.product.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")

    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
       // return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
        List<Category> categories = categoryService.getAllCategory();
       // categories.forEach(category -> category.setProducts(null)); // Set category to null to break the circular reference
        categories.forEach(category -> {
            if (category.getProducts() != null) {
                category.getProducts().forEach(product -> product.setCategory(null));
            }
        });
        // category.getProducts().forEach(product -> product.setCategory(null));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        //return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        Category category = categoryService.getCategoryById(id);
        category.getProducts().forEach(product -> product.setCategory(null)); // Set category to null to break the circular reference
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @PatchMapping("/editCategory/{id}")
    public ResponseEntity<?> editCategory(@RequestBody Category category, @PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.editCategory(category, id), HttpStatus.CREATED);
    }

}
