package com.spring.Shopsy.model;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;


@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = GlobalExceptionMessage.CATEGORY_NAME_BLANK)
    @Size(min = 3, message = GlobalExceptionMessage.CATEGORY_SIZE_INCORRECT)
    private String categoryName;

}
