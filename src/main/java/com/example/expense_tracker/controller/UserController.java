package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.response.ApiResponse;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<User>> getAllUsers() {
        ApiResponse<User> response = new ApiResponse<>();
        response.setData(userService.getUsers());
        response.setMessage("no of users = " + response.getData().size());
        return ResponseEntity.ok(response);
    }
}
