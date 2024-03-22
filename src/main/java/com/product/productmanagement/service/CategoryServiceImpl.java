package com.product.productmanagement.service;

import com.product.productmanagement.model.Category;
import com.product.productmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Category saveCategory(Category category) {

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public String deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id).get();

        if (category != null) {
            categoryRepo.delete(category);
            return "Category Delete Sucessfully";
        }

        return "Something wrong on server";
    }

    @Override

    public Category editCategory(Category p, Integer id) {

        Category oldCategory = categoryRepo.findById(id).get();

        oldCategory.setCategoryName(p.getCategoryName());
        oldCategory.setDescription(p.getDescription());


        return categoryRepo.save(oldCategory);
    }

}
