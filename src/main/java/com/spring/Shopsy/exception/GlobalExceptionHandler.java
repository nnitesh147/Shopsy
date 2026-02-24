package com.spring.Shopsy.exception;


import com.spring.Shopsy.payload.ErrorApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiResponse<Map<String, String>>> methodArgumentNotValid(MethodArgumentNotValidException e){

        Map<String, String> response = new HashMap<>();


        e.getBindingResult().getAllErrors()
                .forEach(err -> {
                    String fieldName = ((FieldError)err).getField();
                    String message = ((FieldError)err).getDefaultMessage();
                    response.put(fieldName, message);
                });

        ErrorApiResponse<Map<String, String>> errorApiResponse = new ErrorApiResponse<>(
                "Invalid Request Body",
                false,
                response
        );


        return new ResponseEntity<ErrorApiResponse<Map<String, String>>>(errorApiResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorApiResponse<String>> resourceNotFound(ResourceNotFoundException e){
        String message = e.getMessage();
        ErrorApiResponse<String> errorApiResponse = new ErrorApiResponse<>(
          message,
          false,
                e.getMessage()
        );
        return new ResponseEntity<ErrorApiResponse<String>>(errorApiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorApiResponse<ApiException>> apiException(ApiException e){
        String message = e.getMessage();
        ErrorApiResponse<ApiException> errorApiResponse = new ErrorApiResponse<>(
                message,
                false,
                null
        );
        return new ResponseEntity<ErrorApiResponse<ApiException>>(errorApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorApiResponse<String>> missingServletRequestParametreException(MissingServletRequestParameterException e){
        ErrorApiResponse<String> errorApiResponse = new ErrorApiResponse<>(
          e.getMessage(),
          false,
          "Please go through request params"
        );
        return new ResponseEntity<ErrorApiResponse<String>>(errorApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSortFilterException.class)
    public ResponseEntity<ErrorApiResponse<InvalidSortFilterException>> invalidSortFilterException(InvalidSortFilterException e){
        ErrorApiResponse<InvalidSortFilterException> errorApiResponse = new ErrorApiResponse<>(
                e.getMessage(),
                false,
                new InvalidSortFilterException(e.getMessage(), e.getALLOWED_METHODS())
        );
        return new ResponseEntity<ErrorApiResponse<InvalidSortFilterException>>(errorApiResponse, HttpStatus.BAD_REQUEST);
    }


}
