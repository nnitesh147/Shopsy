package com.spring.Shopsy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class InvalidSortFilterException extends RuntimeException {

    private String message;
    private List<String> ALLOWED_METHODS;


}
