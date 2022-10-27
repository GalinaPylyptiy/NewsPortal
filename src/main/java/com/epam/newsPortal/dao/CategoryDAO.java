package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);
    Category getCategoryById(Long id);
    Collection<Category> getAll();
    void updateCategory(Category newCategory);
    void delete(Category category);
}
