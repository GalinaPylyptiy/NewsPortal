package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ArticleService {

    void addArticle(Article article);
    Article getArticle(Long id);
    Collection<Article> getAllArticles();
    Collection<Article> getArticlesByCategory(Category category);
    Collection<Article> getArticlesByDate(LocalDateTime localDateTime);
    void updateArticle(Article article);
    void deleteArticle(Article article);
}
