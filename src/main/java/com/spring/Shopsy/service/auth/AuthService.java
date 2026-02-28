package com.spring.Shopsy.service.auth;

import com.spring.Shopsy.exception.ApiException;
import com.spring.Shopsy.model.AppRole;
import com.spring.Shopsy.model.Role;
import com.spring.Shopsy.model.User;
import com.spring.Shopsy.payload.auth.AuthenticationResult;
import com.spring.Shopsy.repository.IRoleRepository;
import com.spring.Shopsy.repository.IUserRepository;
import com.spring.Shopsy.securtiy.jwt.JwtUtils;
import com.spring.Shopsy.securtiy.request.LoginRequest;
import com.spring.Shopsy.securtiy.request.SignUp;
import com.spring.Shopsy.securtiy.response.LoginResponse;
import com.spring.Shopsy.securtiy.services.UserDetailsServiceImpl;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService implements IAuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IRoleRepository roleRepository;



    @Override
    public AuthenticationResult login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsServiceImpl userDetails = (UserDetailsServiceImpl) authentication.getPrincipal();

        String token = jwtUtils.generateTokenFromUserName(userDetails.getUsername());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookieFromToken(token);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(userDetails.getId(),
                userDetails.getUsername(), roles, userDetails.getEmail(), token);

        return new AuthenticationResult(response, jwtCookie);
    }

    @Override
    public AuthenticationResult register(SignUp signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            throw new ApiException("User already exist with this username");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ApiException("User already exist with this email");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                                .orElseThrow(() -> new ApiException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "seller":
                        Role modRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER)
                                .orElseThrow(() -> new ApiException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                                .orElseThrow(() -> new ApiException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user = userRepository.save(user);

        String token = jwtUtils.generateTokenFromUserName(user.getUserName());

        List<String> userRoles = roles.stream()
                .map(item -> item.getRoleName().toString())
                .toList();

        LoginResponse loginResponse = new LoginResponse(user.getUserId(), user.getUserName(), userRoles,
                user.getEmail(), token
        );

        ResponseCookie cookie = jwtUtils.generateJwtCookieFromToken(token);

        return new AuthenticationResult(loginResponse, cookie);
    }
}
