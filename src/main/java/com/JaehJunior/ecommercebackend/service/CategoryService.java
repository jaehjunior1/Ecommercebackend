package com.JaehJunior.ecommercebackend.service;

import com.JaehJunior.ecommercebackend.entity.Category;
import com.JaehJunior.ecommercebackend.exception.ResourceNotFoundException;
import com.JaehJunior.ecommercebackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create Category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get Category By ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    // Update Category
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);

        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    // Delete Category
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}