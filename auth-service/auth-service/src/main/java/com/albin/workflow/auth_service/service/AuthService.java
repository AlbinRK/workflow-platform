package com.albin.workflow.auth_service.service;

import com.albin.workflow.auth_service.dto.LoginRequest;
import com.albin.workflow.auth_service.dto.RegisterRequest;
import com.albin.workflow.auth_service.entity.User;
import com.albin.workflow.auth_service.repository.UserRepository;
import com.albin.workflow.auth_service.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest req){
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setTenantId(req.getTenantId());
        user.setRole("USER");

        userRepository.save(user);
        return "User Registered";
    }

    public String login(LoginRequest req){
        User user =userRepository.findByEmail(req.getEmail())
                .orElseThrow();
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
