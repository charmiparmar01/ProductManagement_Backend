package com.product.productmanagement.controller;

import com.product.productmanagement.model.Product;
import com.product.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct/{categoryId}")
    public ResponseEntity<?> saveProduct(@RequestBody Product product, @PathVariable Integer categoryId) {
        return new ResponseEntity<>(productService.saveProduct(product,categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        products.forEach(product -> {
            if (product.getCategory() != null) {
                product.getCategory().setProducts(null);
            }
        });
        //products.forEach(product -> product.setCategory(null)); // Set category to null to break the circular reference
        return new ResponseEntity<>(products, HttpStatus.OK);
       // return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
//        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        Product product = productService.getProductById(id);
        if (product.getCategory() != null) {
            product.getCategory().setProducts(null);
        }
        //product.setCategory(null); // Set category to null to break the circular reference
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PatchMapping("/editProduct/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product product, @PathVariable Integer id) {
        return new ResponseEntity<>(productService.editProduct(product, id), HttpStatus.CREATED);
    }

    @GetMapping("/product/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Integer categoryId){
       /*List<Product> listOfProducts= productService.findProductsByCategory(categoryId);
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);*/
        List<Product> listOfProducts= productService.findProductsByCategory(categoryId);
        listOfProducts.forEach(product -> product.setCategory(null)); // Set category to null to break the circular reference
        return new ResponseEntity<>(listOfProducts, HttpStatus.OK);
    }

}