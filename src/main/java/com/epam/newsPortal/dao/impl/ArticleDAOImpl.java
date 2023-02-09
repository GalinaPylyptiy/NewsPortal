package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.ArticleDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository

public class ArticleDAOImpl implements ArticleDAO {

    private EntityManagerFactory entityManagerFactory;
    private static final String DATE = "date";
    private static final String CATEGORY = "category";

    @Autowired
    public ArticleDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(article);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Article getArticle(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Article article = entityManager.find(Article.class, id);
        entityManager.close();
        return article;
    }

    @Override
    public Collection <Article> getAllArticles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Article a order by a.whenPosted desc ");
        Collection<Article> articles = query.getResultList();
        entityManager.close();
        return articles;
    }

    @Override
    public Collection<Article> getArticlesByDate(LocalDateTime localDateTime) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Article a where DATE(a.whenPosted) = :date")
                .setParameter(DATE, localDateTime.toLocalDate());
        Collection<Article> articles = query.getResultList();
        entityManager.close();
        return articles;
    }

    @Override
    public Collection<Article> getArticlesByCategory(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select a from Article a where a.category = :category")
                .setParameter(CATEGORY, category);
        Collection<Article> articles = query.getResultList();
        entityManager.close();
        return articles;
    }

    @Override
    public void updateArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(article);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(article);
        transaction.commit();
        entityManager.close();
    }
}
