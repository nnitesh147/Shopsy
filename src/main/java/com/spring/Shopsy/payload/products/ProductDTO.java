package com.spring.Shopsy.payload.products;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    @NotBlank(message = GlobalExceptionMessage.PRODUCT_NAME_DESCRIPTION_BLANK)
    @Size(min = 3, message = GlobalExceptionMessage.PRODUCT_NAME_INCORRECT)
    private String productName;

    @NotBlank(message = GlobalExceptionMessage.PRODUCT_NAME_DESCRIPTION_BLANK)
    @Size(min = 6, message = GlobalExceptionMessage.PRODUCT_DESCRIPTION_INCORRECT)
    private String description;

    private String image;

    @NotNull(message = "Quantity cannot be below 1")
    @Min(value = 1, message = "Quantity cannot be less than 1")
    private Integer quantity;

    @NotNull(message = GlobalExceptionMessage.PRODUCT_PRICE_BLANK)
    @DecimalMin(value = "1.0", message = "Price cannot be less than 1")
    private Double price;

    @JsonSetter(nulls = Nulls.SKIP)
    private Double discount = 0.0;

    private Double specialPrice = this.getPrice();
}
