package com.spring.Shopsy.service.auth;

import com.spring.Shopsy.payload.auth.AuthenticationResult;
import com.spring.Shopsy.securtiy.request.LoginRequest;
import com.spring.Shopsy.securtiy.request.SignUp;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    @Override
    public AuthenticationResult login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthenticationResult register(SignUp signUpRequest) {
        return null;
    }
}
