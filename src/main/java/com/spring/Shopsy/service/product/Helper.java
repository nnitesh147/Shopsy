package com.spring.Shopsy.service.product;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import com.spring.Shopsy.exception.ApiException;
import com.spring.Shopsy.helper.Pagination;
import com.spring.Shopsy.model.Product;
import com.spring.Shopsy.payload.products.ProductDTO;
import com.spring.Shopsy.payload.products.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class Helper {

    public static Pagination getPaginationDetails(Page<Product> pageProduct){

        return new Pagination(
                pageProduct.getNumber(),
                pageProduct.getSize(),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                pageProduct.isLast()
        );
    }
}
