package com.example.expense_tracker.dto.response;

import lombok.Data;

import java.util.Collection;

@Data
public class ApiResponse<T> {
    private String message;
    private String error;
    private Collection<T> data;
}
