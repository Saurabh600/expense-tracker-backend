package com.example.expense_tracker.dto.response;

import com.example.expense_tracker.entity.User;
import lombok.Data;

@Data
public class UserResDto {
    public long id;
    public String name;
    public String emailId;

    public static UserResDto from(User user) {
        UserResDto res = new UserResDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmailId(user.getEmailId());
        return res;
    }
}
