package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.request.ExpenseReqDto;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Expense addExpenseToUser(ExpenseReqDto reqDto) {
        User user = userRepository.findById(reqDto.getUserId()).orElseThrow();
        Category category = categoryRepository.findById(reqDto.getCategoryId()).orElseThrow();

        Expense expense = new Expense();

        expense.setUser(user);
        expense.setCategory(category);
        expense.setDate(reqDto.getDate());
        expense.setDescription(reqDto.getDesc());
        expense.setPrice(reqDto.getPrice());

        user.getExpenses().add(expense);
        category.getExpenses().add(expense);

        return expenseRepository.save(expense);
    }

    public void removeExpense(long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow();
        User user = userRepository.findById(expense.getUser().getId()).orElseThrow();
        Category category = categoryRepository.findById(expense.getCategory().getId()).orElseThrow();

        user.getExpenses().remove(expense);
        category.getExpenses().remove(expense);

        expense.setUser(null);
        expense.setCategory(null);

        expenseRepository.delete(expense);
    }
}
