package com.spring.Shopsy.controller.admin;

import com.spring.Shopsy.constant.Values;
import com.spring.Shopsy.payload.products.ProductDTO;
import com.spring.Shopsy.payload.products.ProductResponse;
import com.spring.Shopsy.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/")
public class UserProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = Values.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = Values.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = Values.DEFAULT_PRODUCT_SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = Values.DEFAULT_SORT_ORDER) String sortOrder
    ){


        ProductResponse product = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<ProductResponse>(product, HttpStatus.OK);

    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductWithCategoryId(
            @RequestParam(name = "pageNumber", defaultValue = Values.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = Values.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = Values.DEFAULT_PRODUCT_SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = Values.DEFAULT_SORT_ORDER) String sortOrder,
            @PathVariable Long categoryId
    ){


        ProductResponse product = productService.getProductWithCategoryId(categoryId, pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<ProductResponse>(product, HttpStatus.OK);

    }
    @GetMapping("/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductWithKeyword(
            @RequestParam(name = "pageNumber", defaultValue = Values.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = Values.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = Values.DEFAULT_PRODUCT_SORT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = Values.DEFAULT_SORT_ORDER) String sortOrder,
            @PathVariable String keyword
    ){

        ProductResponse product = productService.getProductWithKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<ProductResponse>(product, HttpStatus.FOUND);

    }
}
