package com.example.expense_tracker.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryReqDto {
    @NotNull
    public long userId;

    @NotNull
    public String categoryName;
}
