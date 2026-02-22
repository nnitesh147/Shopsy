package com.spring.Shopsy.controller.user;

import com.spring.Shopsy.constant.Values;
import com.spring.Shopsy.exception.ResourceNotFoundException;
import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.payload.category.CategoryResponse;
import com.spring.Shopsy.service.Category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/public/")
public class UserCategoryController {


    private CategoryService categoryService;

    @Autowired
    public UserCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = Values.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = Values.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = Values.DEFAULT_CATEGORY_SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = Values.DEFAULT_SORT_ORDER) String sortOrder

    ){
        CategoryResponse response = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        CategoryDTO response = categoryService.createCategory(category);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDTO category){

        CategoryDTO response = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<CategoryDTO>(response, HttpStatus.OK);

    }


}
