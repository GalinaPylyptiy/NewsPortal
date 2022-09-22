package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import com.epam.newsPortal.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Controller
@RequestMapping(value = "/article", produces = "text/plain;charset=UTF-8")

public class ArticleController {

    private ArticleService articleService;
    private CategoryService categoryService;
    private EditorService editorService;


    @Autowired
    public ArticleController(ArticleService articleService, CategoryService categoryService, EditorService editorService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.editorService = editorService;

    }
    @GetMapping("/addArticle")
    public String addArticle(@ModelAttribute("article")Article article,@RequestParam("editorId")Long editorId, Model model){
        model.addAttribute("categoryList", categoryService.getAll());
        Editor editor = editorService.getEditor(editorId);
        System.out.println("Editor from /addArticle "+ editor);
        model.addAttribute("editor", editor);
        return "article/addArticle";
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute("article")Article article,@RequestParam("editorId")String editorId){
        Long categoryId =article.getCategory().getId();
        Category category = categoryService.getCategoryById(categoryId);
        Editor editor = editorService.getEditor(Long.parseLong(editorId));
        System.out.println("Editor from /save "+ editor);
        article.setEditor(editor);
        article.setCategory(category);
        article.setDayPosted(LocalDate.now());
        article.setTimePosted(LocalTime.now());
        articleService.addArticle(article);
        return "article/seeArticle";
    }
}
