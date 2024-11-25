package com.example.expense_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseReqDto {
    @NotNull
    private long userId;

    @NotNull
    private long categoryId;

    @Min(0)
    private BigDecimal price;

    private String desc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
