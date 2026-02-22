package com.spring.Shopsy.payload.category;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    @NotBlank(message = GlobalExceptionMessage.CATEGORY_NAME_BLANK)
    @Size(min = 3, message = GlobalExceptionMessage.CATEGORY_SIZE_INCORRECT)
    private String categoryName;


}
