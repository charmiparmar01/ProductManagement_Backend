package com.product.productmanagement.service;

import com.product.productmanagement.model.Product;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product, Integer catId);

    public List<Product> getAllProduct();

    public Product getProductById(Integer id);

    public String deleteProduct(Integer id);

    public Product editProduct(Product product,Integer id);

    public List<Product> findProductsByCategory(Integer categoryId);
}
