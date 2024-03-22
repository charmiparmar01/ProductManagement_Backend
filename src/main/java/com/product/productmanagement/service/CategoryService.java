package com.product.productmanagement.service;

import com.product.productmanagement.model.Category;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);

    public List<Category> getAllCategory();

    public Category getCategoryById(Integer id);

    public String deleteCategory(Integer id);

    public Category editCategory(Category category,Integer id);
}
