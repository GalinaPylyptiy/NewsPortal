package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ArticleDAO {

    void addArticle(Article article);
    Article getArticle(Long id);
    Collection<Article> getAllArticles();
    Collection<Article> getArticlesByDate(LocalDateTime localDateTime);
    Collection<Article> getArticlesByCategory(Category category);
    void updateArticle(Article article);
    void deleteArticle(Article article);

}
