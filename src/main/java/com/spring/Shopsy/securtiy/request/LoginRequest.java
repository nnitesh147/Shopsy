package com.spring.Shopsy.securtiy.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {


    @NotBlank(message = "userName should not be blank")
    private String username;

    @NotBlank(message = "Password should not be blank")
    private String password;



}
