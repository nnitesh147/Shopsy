package com.spring.Shopsy.securtiy.response;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {

    private Long id;
    private String jwtToken;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponse(Long id, String username, List<String> roles, String email, String jwtToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.email = email;
        this.jwtToken = jwtToken;
    }

    public LoginResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
