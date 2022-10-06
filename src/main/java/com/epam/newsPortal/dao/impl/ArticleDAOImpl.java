package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.ArticleDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @Transactional
    public void addArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(article);
        transaction.commit();
        entityManager.close();
    }

    @Override
    @Transactional
    public Article getArticle(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Article article = entityManager.find(Article.class, id);
        entityManager.close();
        return article;
    }

    @Override
    @Transactional
    public List<Article> getAllArticles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Article", Article.class);
        List<Article> articles = query.getResultList();
        entityManager.close();
        return articles;
    }

    @Override
    @Transactional
    public List<Article> getArticlesByDate(LocalDateTime localDateTime) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Article a where DATE(a.whenPosted) = :date")
                .setParameter("date", localDateTime.toLocalDate());
        return (List<Article>) query.getResultList();
    }

    @Override
    public List<Article> getArticlesByCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Article a where a.category = :category")
                .setParameter("category", category);
        List<Article> articles = query.getResultList();
        entityManager.close();
        return articles;
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(article);
        transaction.commit();
        entityManager.close();
    }

    @Override
    @Transactional
    public void deleteArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(article);
        transaction.commit();
        entityManager.close();
    }
}
