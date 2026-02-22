package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.payload.category.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO category);
}
