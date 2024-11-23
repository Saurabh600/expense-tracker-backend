package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.response.ApiResponse;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        ApiResponse<User> response = new ApiResponse<User>();

        User newUser = userService.creatNewUser(user);
        response.setMessage("user created successfully");
        response.setData(List.of(newUser));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
