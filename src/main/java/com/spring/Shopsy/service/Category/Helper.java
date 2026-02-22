package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.helper.Pagination;
import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.payload.category.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class Helper {


    public static Pagination getPaginationDetails(Page<Category> pageCategory){

        return new Pagination(
                pageCategory.getNumber(),
                pageCategory.getSize(),
                pageCategory.getTotalPages(),
                pageCategory.getTotalElements(),
                pageCategory.isLast()
        );
    }

}
