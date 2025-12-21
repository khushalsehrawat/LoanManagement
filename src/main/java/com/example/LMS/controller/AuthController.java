package com.example.LMS.controller;


import com.example.LMS.dto.auth.LoginRequest;
import com.example.LMS.dto.auth.LoginResponse;
import com.example.LMS.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
    {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody LoginRequest loginRequest)
    {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(token);
    }

}
