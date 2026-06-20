package com.expense.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.expense.tracker.dto.LoginRequestDTO;
import com.expense.tracker.entity.User;
import com.expense.tracker.jwt.JwtUtil;
import com.expense.tracker.repository.UserRepository;
import com.expense.tracker.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user != null &&
                user.getPassword().equals(request.getPassword())) {

            return JwtUtil.generateToken(
                    user.getEmail());
        }

        return "Invalid Credentials";
    }
}