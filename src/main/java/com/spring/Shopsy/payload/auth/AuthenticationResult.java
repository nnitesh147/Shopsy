package com.spring.Shopsy.payload.auth;

import com.spring.Shopsy.securtiy.response.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseCookie;

@Data
@AllArgsConstructor
public class AuthenticationResult {

    private final LoginResponse loginResponse;

    private final ResponseCookie jwtCookie;


}
