package com.spring.Shopsy.service.auth;

import com.spring.Shopsy.payload.auth.AuthenticationResult;
import com.spring.Shopsy.securtiy.request.LoginRequest;
import com.spring.Shopsy.securtiy.request.SignUp;

public interface IAuthService {


    AuthenticationResult login(LoginRequest loginRequest);

    AuthenticationResult register(SignUp signUpRequest);



}
