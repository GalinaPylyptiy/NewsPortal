package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.CATEGORY_LIST;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.FRESH_NEWS;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.USER;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.EDITOR_SIGN_IN;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.INDEX_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.USER_REGISTER;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.USER_SIGN_IN;

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
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        LocalDateTime localDateTime = LocalDateTime.now();
        model.addAttribute(FRESH_NEWS, articleService.getArticlesByDate(localDateTime));
        return INDEX_PAGE;
    }

    @GetMapping("/editorLogin")
    public String enterAsEditor(){
      return EDITOR_SIGN_IN;
    }

    @GetMapping("/userLogin")
    public String enterAsUser(@ModelAttribute(USER) User user){
        return USER_SIGN_IN;
    }

    @GetMapping("/logout")
    public String logout(){
        return REDIRECT;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute(USER) User user){
        return USER_REGISTER;
    }

}
