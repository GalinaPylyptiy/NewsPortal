package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.CategoryDAO;
import com.epam.newsPortal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;


@Repository

public class CategoryDAOImpl implements CategoryDAO {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public CategoryDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public Category getCategoryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Category category = entityManager.find(Category.class, id);
        entityManager.close();
        return category;
    }

    @Override
    public Collection<Category> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select c from Category c", Category.class);
        Collection<Category> categories = query.getResultList();
        entityManager.close();
        return categories;
    }

    @Override
    public void updateCategory(Category newCategory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       entityManager.refresh(newCategory);
       entityManager.close();
    }

    @Override
    public void delete(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(category);
        entityManager.close();
    }
}
