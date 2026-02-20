package com.spring.Shopsy.controller.user;

import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.service.Category.CategoryService;
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
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added Successfully", HttpStatus.CREATED);
    }

    @PutMapping("categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        try {
            categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<String>("Category Updated", HttpStatus.OK);
        }catch (ResponseStatusException ex){
            return new ResponseEntity<String>("Resource Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
