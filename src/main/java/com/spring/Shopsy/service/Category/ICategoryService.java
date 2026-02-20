package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();

    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Long categoryId, Category category);
}
