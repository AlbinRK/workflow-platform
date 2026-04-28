package com.albin.workflow.auth_service.controller;

import com.albin.workflow.auth_service.dto.LoginRequest;
import com.albin.workflow.auth_service.dto.RegisterRequest;
import com.albin.workflow.auth_service.service.AuthService;
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

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        return authService.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req){
        return authService.login(req);
    }
}
