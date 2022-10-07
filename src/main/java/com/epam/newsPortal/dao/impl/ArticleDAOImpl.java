package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.ArticleDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Component
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ArticleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addArticle(Article article) {
        Session session = sessionFactory.openSession();
        session.save(article);
        session.close();
    }

    @Override
    @Transactional
    public Article getArticle(Long id) {
        Session session = sessionFactory.openSession();
        Article article = session.get(Article.class, id);
        session.close();
        return article;
    }

    @Override
    @Transactional
    public List<Article> getArticles(Category category) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Article", Article.class);
        List<Article> articles = query.getResultList();
        session.close();
        return articles;
    }

    @Override
    public List<Article> getArticlesByDate(LocalDate localDate) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("select*from article where article.dayposted = :date")
                .setParameter("date", localDate).addEntity(Article.class);
        return (List<Article>) query.getResultList();
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        Session session = sessionFactory.openSession();
        session.update(article);
        session.close();
    }

    @Override
    @Transactional
    public void deleteArticle(Article article) {
        Session session = sessionFactory.openSession();
        session.delete(article);
        session.close();
    }
}
