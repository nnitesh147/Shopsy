package com.spring.Shopsy.constant;

import java.util.List;

public class Values {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_CATEGORY_SORT_BY = "categoryId";
    public static final String DEFAULT_SORT_ORDER = "asc";
    public static final String DEFAULT_PRODUCT_SORT_BY = "productId";

    public static final List<String> PRODUCT_ALLOWED_SORT_FILTERS = List.of(
       "productId", "productName" , "quantity", "price" , "discount" , "specialPrice"
    ) ;

    public static final List<String> CATEGORY_ALLOWED_SORT_FILTERS = List.of(
            "categoryId", "categoryName"
    ) ;

}
