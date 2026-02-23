package com.spring.Shopsy.service.product;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import com.spring.Shopsy.exception.ApiException;
import com.spring.Shopsy.exception.ResourceNotFoundException;
import com.spring.Shopsy.helper.Pagination;
import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.model.Product;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.payload.category.CategoryResponse;
import com.spring.Shopsy.payload.products.ProductDTO;
import com.spring.Shopsy.payload.products.ProductResponse;
import com.spring.Shopsy.repository.ICategoryRepository;
import com.spring.Shopsy.repository.IProductRepository;
import com.spring.Shopsy.service.product.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                     new ResourceNotFoundException("Category", "CategoryId", categoryId)
                    );

        Product product = modelMapper.map(productDTO, Product.class);

        double specialPrice = product.getPrice() -
                ((product.getDiscount()*0.01)*product.getPrice());

        product.setCategory(category);
        product.setSpecialPrice(specialPrice);
        product.setImage("default.png");



        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = productRepository.findAll(pageDetails);

        List<Product> products = productPage.getContent();

        if(products.isEmpty()){
            throw new ApiException(GlobalExceptionMessage.NO_PRODUCT_PRESENT);
        }

        List<ProductDTO> response = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();


        Pagination paginationDetails = Helper.getPaginationDetails(productPage);

        return new ProductResponse(response, paginationDetails);


    }

    @Override
    public ProductResponse getProductWithCategoryId(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {


        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "CategoryId", categoryId)
                );

        Sort sort = sortOrder.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = productRepository.findByCategory(category, pageDetails);

        List<Product> products = productPage.getContent();

        if(products.isEmpty()){
            throw new ApiException(GlobalExceptionMessage.NO_PRODUCT_PRESENT);
        }

        List<ProductDTO> response = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();


        Pagination paginationDetails = Helper.getPaginationDetails(productPage);

        return new ProductResponse(response, paginationDetails);

    }

    @Override
    public ProductResponse getProductWithKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%', pageDetails);

        List<Product> products = productPage.getContent();

        if(products.isEmpty()){
            throw new ApiException(GlobalExceptionMessage.NO_PRODUCT_PRESENT);
        }

        List<ProductDTO> response = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();


        Pagination paginationDetails = Helper.getPaginationDetails(productPage);

        return new ProductResponse(response, paginationDetails);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "productId", productId));

        double specialPrice = productDTO.getPrice() -
                ((productDTO.getDiscount()*0.01)*productDTO.getPrice());

        product.setSpecialPrice(specialPrice);
        product.setProductName(productDTO.getProductName());
        product.setDiscount(productDTO.getDiscount());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);

        return modelMapper.map(product, ProductDTO.class);

    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "productId", productId));


        productRepository.delete(product);

        return modelMapper.map(product, ProductDTO.class);

    }


}
