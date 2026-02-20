package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    private  List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId((long)categories.size());
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));

        categories.remove(category);

        return "Category with categoryId: " + categoryId;
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {

        Optional<Category> existingCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();


        if(existingCategory.isPresent()){
            existingCategory.get().setCategoryName(category.getCategoryName());
            return category;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
        }

    }
}
