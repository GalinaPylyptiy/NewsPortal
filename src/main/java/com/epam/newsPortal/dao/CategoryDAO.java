package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Category;
import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);
    Category getCategoryById(Long id);
    List<Category> getAll();
    void updateCategory(Category newCategory);
    void delete(Category category);
}
