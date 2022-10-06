package com.epam.newsPortal.controller;

import com.epam.newsPortal.dto.ArticleDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import com.epam.newsPortal.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ARTICLE;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ARTICLES;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.CATEGORY_ID;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.CATEGORY_LIST;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ID;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.ARTICLE_EDIT;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.ARTICLE_LIST;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.INDEX_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.NEW_ARTICLE;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT_ARTICLE;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.SEE_ARTICLE;

@Controller
@RequestMapping(value = "/article", produces = "text/plain;charset=UTF-8")

public class ArticleController {

    private ArticleService articleService;
    private CategoryService categoryService;
    private ArticleMapper articleMapper;

    @Autowired

    public ArticleController(ArticleService articleService, CategoryService categoryService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("")
    public String showAllArticles(Model model){
        model.addAttribute(ARTICLES, articleService.getAllArticles());
        return ARTICLE_LIST;
    }

    @GetMapping("/category")
    public String getArticlesByCategory( @RequestParam(CATEGORY_ID) String categoryId,Model model ) {
        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        model.addAttribute(ARTICLES,articleService.getArticlesByCategory(category));
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return INDEX_PAGE;
    }


    @GetMapping("/new")
    public String addArticle(@ModelAttribute(ARTICLE)Article article,Model model){
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return NEW_ARTICLE;
    }

    @GetMapping("/{id}/edit")
    public String updateArticle(@PathVariable(ID) Long id, Model model){
       model.addAttribute(ARTICLE, articleService.getArticle(id));
       model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return ARTICLE_EDIT;
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable(ID) Long id){
       Article article  = articleService.getArticle(id);
       articleService.deleteArticle(article);
        return REDIRECT_ARTICLE;
    }

    @PostMapping("/edit")
    public String saveUpdated(@ModelAttribute(ARTICLE)Article article,
                              @RequestParam (CATEGORY_ID) String categoryId){
        ArticleDTO articleDTO = articleMapper.setProperties(Long.parseLong(categoryId), article.getWhenPosted());
        articleMapper.map(article, articleDTO);
        articleService.updateArticle(article);
        return REDIRECT_ARTICLE;
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute(ARTICLE)Article article,
                              @RequestParam(CATEGORY_ID) String categoryId){
        ArticleDTO articleDTO = articleMapper.setProperties(Long.parseLong(categoryId), LocalDateTime.now());
        articleMapper.map(article, articleDTO);
        articleService.addArticle(article);
        return SEE_ARTICLE;
    }
}
