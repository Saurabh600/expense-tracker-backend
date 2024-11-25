package com.example.expense_tracker.service;

import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategoryToUser(long userId, String categoryName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user with given id not found"));
        Category category = new Category();
        category.setUser(user);
        category.setCategoryName(categoryName);

        user.getCategories().add(category);

        return categoryRepository.save(category);
    }

    public void removeCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("category with given id not found"));
        User user = userRepository.findById(category.getUser().getId()).orElseThrow(() -> new NoSuchElementException("user with given id not found"));

        user.getCategories().remove(category);

        category.setUser(null);

        categoryRepository.delete(category);
    }
}
