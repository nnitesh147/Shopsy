package com.spring.Shopsy.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUid = 1L;


    public ApiException(){

    }

    public ApiException(String message){
        super(message);
    }



}
