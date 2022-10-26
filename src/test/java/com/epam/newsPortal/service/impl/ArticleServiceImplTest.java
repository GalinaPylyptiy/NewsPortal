package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.config.TestBeanConfig;
import com.epam.newsPortal.dao.ArticleDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
@SpringBootTest

class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleDAO articleDAO;


    @Test
    @DisplayName("Test getArticleById Success")
    void getArticleByIdShouldReturnArticle() {
        doReturn(getArticleInstance()).when(articleDAO).getArticle(1L);
        Article article = articleService.getArticle(1L);
        Assertions.assertNotNull(article);
        Assertions.assertEquals(article, getArticleInstance());
    }

    @Test
    @DisplayName("Test getArticleByDate Success")
    void getArticlesByDateShouldReturnListOfArticles() {
        List <Article> articleList = Arrays.asList(getArticleInstance());
        doReturn(articleList).when(articleDAO).getArticlesByDate(articleList.get(0).getWhenPosted());
        List<Article> articles = articleService.getArticlesByDate(articleList.get(0).getWhenPosted());
        Assertions.assertEquals(1, articles.size());


    }

    private Article getArticleInstance(){
        Article article = new Article();
        article.setId(1L);
        article.setTitle("Title");
        article.setBody("The application we're going to use in this article is an API that provides some basic operations on an Employee Resource. This is a typical tiered architecture — the API call is processed from the Controller to Service to the Persistence layer.");
        article.setWhenPosted(LocalDateTime.of(2022,5,13,20,05));
        Category category = new Category();
        category.setId(1L);
        category.setName("Education");
        article.setCategory(category);
        return article;
    }
}