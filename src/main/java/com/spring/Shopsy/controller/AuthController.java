package com.spring.Shopsy.controller;

import com.spring.Shopsy.payload.auth.AuthenticationResult;
import com.spring.Shopsy.securtiy.request.LoginRequest;
import com.spring.Shopsy.securtiy.request.SignUp;
import com.spring.Shopsy.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth/")
public class AuthController {


    @Autowired
    private AuthService authService;



    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthenticationResult result = authService.login(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        result.getJwtCookie().toString())
                .body(result.getLoginResponse());
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUpRequest) {
        AuthenticationResult result = authService.register(signUpRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        result.getJwtCookie().toString())
                .body(result.getLoginResponse());
    }

    @PostMapping("signout")
    public ResponseEntity<?> signOutUser() {
        AuthenticationResult result = authService.signOut();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        result.getJwtCookie().toString())
                .body(result.getMessageResponse());
    }
}
