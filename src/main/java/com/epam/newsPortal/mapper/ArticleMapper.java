package com.epam.newsPortal.mapper;

import com.epam.newsPortal.dto.ArticleDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ArticleMapper {

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private ArticleDTO articleDTO;

    public ArticleMapper() {
    }

    public ArticleDTO setProperties (Long categoryId, LocalDateTime whenPosted) {
    Category category = categoryService.getCategoryById(categoryId);
    articleDTO.setCategory(category);
    articleDTO.setWhenPosted(whenPosted);
    return articleDTO;
    }

    public Article map(Article article, ArticleDTO articleDTO){
        article.setCategory(articleDTO.getCategory());
        article.setWhenPosted(articleDTO.getWhenPosted());
        return article;
    }
}
