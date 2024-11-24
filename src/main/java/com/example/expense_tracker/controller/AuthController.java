package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.request.AuthReqDto;
import com.example.expense_tracker.dto.request.UserReqDto;
import com.example.expense_tracker.dto.response.ApiResponse;
import com.example.expense_tracker.dto.response.UserResDto;
import com.example.expense_tracker.service.JwtService;
import com.example.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<UserResDto>> register(@RequestBody @Valid UserReqDto user) {
        ApiResponse<UserResDto> response = new ApiResponse<>();

        var newUser = userService.createNewUser(user);
        response.setMessage("user created successfully");
        response.setData(List.of(newUser));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/token")
    public ResponseEntity<String> getJwtToken(@RequestBody @Valid AuthReqDto authReq) {
        try {
            userService.verify(authReq);
            String token = jwtService.generateToken(authReq.getEmailId());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

        }
    }
}
