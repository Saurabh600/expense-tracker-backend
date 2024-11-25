package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.request.ExpenseReqDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody @Valid ExpenseReqDto reqDto) {
        Expense expense = expenseService.addExpenseToUser(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeExpense(
            @PathVariable(value = "id") long expenseId
    ) {
        expenseService.removeExpense(expenseId);
        return ResponseEntity.accepted().build();
    }
}
