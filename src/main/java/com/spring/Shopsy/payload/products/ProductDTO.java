package com.spring.Shopsy.payload.products;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    @NotBlank(message = "Product name cannot be empty")
    @Size(min = 3, message = "Invalid product name")
    private String productName;

    private String description;

    private String image;

    @NotNull(message = "Quantity cannot be below 1")
    @Min(value = 1, message = "Quantity cannot be less than 1")
    private Integer quantity;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin(value = "1.0", message = "Price cannot be less than 1")
    private Double price;

    @JsonSetter(nulls = Nulls.SKIP)
    private Double discount = 0.0;

    private Double specialPrice = this.getPrice();
}
