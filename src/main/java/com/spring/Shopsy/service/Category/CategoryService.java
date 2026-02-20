package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import com.spring.Shopsy.exception.ApiException;
import com.spring.Shopsy.exception.ResourceNotFoundException;
import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    private ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()){
            throw new ApiException(GlobalExceptionMessage.NO_CATEGORY_PRESENT);
        }

        return categories;
    }

    @Override
    public void createCategory(Category category) {

        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if(savedCategory != null){
            throw new ApiException(GlobalExceptionMessage.CATEGORY_ALREADY_EXIST + category.getCategoryName());
        }

        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return "Category with categoryId: " + categoryId;
        }else {

            throw new ResourceNotFoundException("Category", "CategoryId", categoryId);

        }
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {

        Category savedOptionalCategory =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));


        savedOptionalCategory.setCategoryName(category.getCategoryName());

        savedOptionalCategory = categoryRepository.save(savedOptionalCategory);

        return savedOptionalCategory;
    }
}
