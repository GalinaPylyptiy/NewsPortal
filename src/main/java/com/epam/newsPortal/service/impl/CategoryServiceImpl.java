package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.CategoryDAO;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);

    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public Collection<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public void updateCategory(Category newCategory) {
        categoryDAO.updateCategory(newCategory);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}
