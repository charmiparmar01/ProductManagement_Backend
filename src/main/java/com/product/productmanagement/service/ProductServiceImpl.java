package com.product.productmanagement.service;

import com.product.productmanagement.model.Category;
import com.product.productmanagement.model.Product;
import com.product.productmanagement.repository.CategoryRepository;
import com.product.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepository
            ;
    @Override
    public Product saveProduct(Product product, Integer catId) {

        Category category = this.categoryRepository.findById(catId).get();
        product.setCategory(category);
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        //return productRepo.findById(id).get();
        Optional<Product> product= this.productRepo.findById(id);
        if(product.isPresent()){
             Product p= product.get();
             return p;
        }
        return null;
    }

    @Override
    public String deleteProduct(Integer id) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            productRepo.delete(product.get());
            productRepo.flush();
            return "Product Deleted Successfully";
        }
        return "Something wrong on server";
    }

    @Override
    public Product editProduct(Product p, Integer id) {

        Product oldProduct = productRepo.findById(id).get();

        oldProduct.setProductName(p.getProductName());
        oldProduct.setDescription(p.getDescription());
        oldProduct.setPrice(p.getPrice());
        oldProduct.setStatus(p.getStatus());
        //oldProduct.setCategories(p.getCategories());

        return productRepo.save(oldProduct);
    }

    @Override
    public List<Product> findProductsByCategory(Integer categoryId) {
//       Optional< List<Product>> productList= productRepo.findByCategory(categoryId);
//       if(productList.isPresent()){
//           List<Product> p= productList.get();
//           return p;
//       }
        List<Product> productList= productRepo.findByCategoryId(categoryId).orElse( null);
        return productList;
    }
}