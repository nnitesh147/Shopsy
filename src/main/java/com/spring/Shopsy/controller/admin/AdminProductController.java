package com.spring.Shopsy.controller.admin;

import com.spring.Shopsy.payload.products.ProductDTO;
import com.spring.Shopsy.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long categoryId){


        ProductDTO product = productService.addProduct(productDTO, categoryId);

        return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);

    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long productId){


        ProductDTO product = productService.updateProduct(productDTO, productId);

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){


        ProductDTO product = productService.deleteProduct(productId);

        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);

    }

}
