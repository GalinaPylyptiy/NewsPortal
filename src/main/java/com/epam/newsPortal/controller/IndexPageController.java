package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexPageController {

    private CategoryService categoryService;
    private ArticleService articleService;

    @Autowired
    public IndexPageController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @GetMapping("")
    public String getIndexPage( Model model){
        model.addAttribute("categoryList", categoryService.getAll());
        LocalDate localDate = LocalDate.now();
        model.addAttribute("freshNews", articleService.getArticlesByDate(localDate));
        return "indexPage";
    }

    @GetMapping("/editorLogin")
    public String enterAsEditor(){
      return "editor/signIn";
    }
}
