package com.spring.Shopsy.securtiy.request;

import com.spring.Shopsy.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignUp {

    @NotBlank(message = "userName should not be blank")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Password should not be blank")
    private String password;

    @NotBlank(message = "Email should not be blank")
    @Size(max = 50)
    @Email
    private String email;

    @Getter
    @Setter
    private Set<String> role;

}
