package com.spring.Shopsy.payload.category;

import com.spring.Shopsy.helper.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private List<CategoryDTO> content;
    private Pagination pagination;

}
