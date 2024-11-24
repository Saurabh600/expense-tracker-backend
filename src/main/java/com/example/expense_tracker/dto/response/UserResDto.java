package com.example.expense_tracker.dto.response;

import com.example.expense_tracker.entity.User;
import lombok.Data;

@Data
public class UserResDto {
    public long id;
    public String name;
    public String emailId;
}
