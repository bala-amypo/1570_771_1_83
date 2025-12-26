package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {

        return ResponseEntity.ok(
                userService.register(email, password, role)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password) {

        User user = userService.login(email, password);

        String token = jwtTokenProvider.createToken(
                user.getEmail(),
                user.getRole(),
                user.getId()
        );

        return ResponseEntity.ok(token);
    }
}
