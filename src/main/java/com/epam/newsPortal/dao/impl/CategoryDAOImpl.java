package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.CategoryDAO;
import com.epam.newsPortal.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        Session session = sessionFactory.openSession();
        session.persist(category);
        session.close();
    }

    @Transactional
    @Override
    public Category getCategoryById(Long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    @Transactional
    public List<Category> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Category", Category.class);
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }

    @Override
    @Transactional
    public void updateCategory(Category newCategory) {
        Session session = sessionFactory.openSession();
        session.update(newCategory);
        session.close();
    }

    @Override
    @Transactional
    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        session.delete(category);
        session.close();
    }
}
