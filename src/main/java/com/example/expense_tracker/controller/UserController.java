package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.response.ApiResponse;
import com.example.expense_tracker.dto.response.UserResDto;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.entity.UserPrincipal;
import com.example.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getAllUsers() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getUserByEmailId(userPrincipal.getUsername()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResDto> getUserById(@PathVariable(value = "id") long userID) {
        return ResponseEntity.ok(userService.getUserById(userID));
    }
}
