package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {
    void addArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    List<Article> getArticlesByCategory(Category category);
    List<Article> getArticlesByDate(LocalDateTime localDateTime);
    void updateArticle(Article article);
    void deleteArticle(Article article);
}
