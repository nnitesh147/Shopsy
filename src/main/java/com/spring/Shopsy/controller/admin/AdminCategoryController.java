package com.spring.Shopsy.controller.admin;

import com.spring.Shopsy.exception.ResourceNotFoundException;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/admin/")
public class AdminCategoryController {

    private CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){

        CategoryDTO categoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);

    }

}
