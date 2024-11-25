package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.request.CategoryReqDto;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(
            @RequestBody @Valid CategoryReqDto reqDto) {
        Category newCategory = categoryService.addCategoryToUser(reqDto.getUserId(), reqDto.getCategoryName());
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeCategory(
            @PathVariable(value = "id") long categoryId
    ) {
        categoryService.removeCategory(categoryId);
        return ResponseEntity.accepted().build();
    }
}
