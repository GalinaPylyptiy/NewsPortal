package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.ArticleDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;

   @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public void addArticle(Article article) {
      articleDAO.addArticle(article);
    }

    @Override
    public Article getArticle(Long id) {
        return articleDAO.getArticle(id);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    @Override
    public List<Article> getArticlesByCategory(Category category) {
        return articleDAO.getArticlesByCategory(category);
    }

    @Override
    public List<Article> getArticlesByDate(LocalDateTime localDateTime) {
        return articleDAO.getArticlesByDate(localDateTime);
    }

    @Override
    public void updateArticle(Article article) {
    articleDAO.updateArticle(article);
    }

    @Override
    public void deleteArticle(Article article) {
    articleDAO.deleteArticle(article);
    }
}
