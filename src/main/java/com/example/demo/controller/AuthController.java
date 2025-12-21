package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.status(201)
                .body(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody Map<String, String> request) {

        User user = userService.login(
                request.get("email"),
                request.get("password"));

        return ResponseEntity.status(200).body(user);
    }
}
