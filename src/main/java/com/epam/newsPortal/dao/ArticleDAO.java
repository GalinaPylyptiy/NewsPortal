package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import java.time.LocalDateTime;
import java.util.List;

public interface ArticleDAO {

    void addArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    List<Article> getArticlesByDate(LocalDateTime localDateTime);
    List<Article> getArticlesByCategory(Category category);
    void updateArticle(Article article);
    void deleteArticle(Article article);

}
