package com.spring.Shopsy.model;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = GlobalExceptionMessage.PRODUCT_NAME_DESCRIPTION_BLANK)
    @Size(min = 3, message = GlobalExceptionMessage.PRODUCT_NAME_INCORRECT)
    private String productName;

    @NotBlank(message = GlobalExceptionMessage.PRODUCT_NAME_DESCRIPTION_BLANK)
    @Size(min = 6, message = GlobalExceptionMessage.PRODUCT_DESCRIPTION_INCORRECT)
    private String description;
    private Integer quantity;
    private String image;

    @NotNull(message = GlobalExceptionMessage.PRODUCT_PRICE_BLANK)
    @DecimalMin(value = "1.0", message = "Price cannot be less than 1")
    private double price;

    private double specialPrice;
    private double discount;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
