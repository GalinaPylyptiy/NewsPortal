package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Category;

import java.util.Collection;


public interface CategoryService {
    void addCategory(Category category);
    Category getCategoryById(Long id);
    Collection<Category> getAll();
    void updateCategory(Category newCategory);
    void delete(Category category);
}
