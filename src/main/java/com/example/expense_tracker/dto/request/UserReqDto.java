package com.example.expense_tracker.dto.request;

import com.example.expense_tracker.entity.User;
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

    public static User toUser(UserReqDto reqDto) {
        User user = new User();
        user.setName(reqDto.getName());
        user.setEmailId(reqDto.getEmailId());
        user.setPassword(reqDto.getPassword());
        return user;
    }
}
