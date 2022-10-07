package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {
    void addArticle(Article article);
    Article getArticle(Long id);
    List<Article> getArticles(Category category);
    List<Article> getArticlesByDate(LocalDate localDate);
    void updateArticle(Article article);
    void deleteArticle(Article article);
}
