package com.epam.newsPortal.mapper;

import com.epam.newsPortal.dao.CategoryDAO;
import com.epam.newsPortal.dto.ArticleDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ArticleMapper {

    private CategoryDAO categoryDAO;

    @Autowired
    public ArticleMapper(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void setProperties (Long categoryId, LocalDateTime whenPosted, ArticleDTO articleDTO) {
    Category category = categoryDAO.getCategoryById(categoryId);
    articleDTO.setCategory(category);
    articleDTO.setWhenPosted(whenPosted);
    }

    public Article toModel(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setCategory(articleDTO.getCategory());
        article.setTitle(articleDTO.getTitle());
        article.setBody(articleDTO.getBody());
        article.setWhenPosted(articleDTO.getWhenPosted());
        return article;
    }

    public void setArticleId(Article article, Long id){
        article.setId(id);
    }

    public ArticleDTO toArticleDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setCategory(article.getCategory());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setBody(article.getBody());
        articleDTO.setWhenPosted(article.getWhenPosted());
        return  articleDTO;
    }
}
