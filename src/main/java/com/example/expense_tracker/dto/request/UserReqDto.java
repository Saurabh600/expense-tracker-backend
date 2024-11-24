package com.example.expense_tracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserReqDto {
    @NotNull
    public String name;
    @Email
    public String emailId;
    @NotNull
    @Length(min = 7, max = 15)
    public String password;
}
