package com.spring.Shopsy.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorApiResponse <T> {

    private String message;
    private boolean status;
    private T error;

}
