package com.spring.Shopsy.service.product;

import com.spring.Shopsy.payload.category.CategoryResponse;
import com.spring.Shopsy.payload.products.ProductDTO;
import com.spring.Shopsy.payload.products.ProductResponse;
import jakarta.validation.Valid;

public interface IProductService {

    ProductDTO addProduct(ProductDTO productDTO, Long categoryid);
    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse getProductWithCategoryId(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse getProductWithKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDTO updateProduct(@Valid ProductDTO productDTO, Long productId);

    ProductDTO deleteProduct(Long productId);
}
