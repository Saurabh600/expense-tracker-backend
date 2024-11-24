package com.example.expense_tracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthReqDto {
    @Email
    public String emailId;
    @NotNull
    public String password;
}
