package com.epam.newsPortal.controller;

import com.epam.newsPortal.dto.UserDTO;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.epam.newsPortal.constants.AttributeNamesConstants.CATEGORY_LIST;
import static com.epam.newsPortal.constants.AttributeNamesConstants.FRESH_NEWS;
import static com.epam.newsPortal.constants.AttributeNamesConstants.USER_DTO;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.INDEX_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.USER_REGISTER_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.USER_SIGN_IN_PAGE_PATH;

@Controller

@RequestMapping("/")

public class IndexPageController {

    private final CategoryService categoryService;
    private final ArticleService articleService;

    @Autowired
    public IndexPageController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;

    }

    @GetMapping("")
    public String getIndexPage(Model model){
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        model.addAttribute(FRESH_NEWS, articleService.getAllArticles());
        return INDEX_PAGE_PATH;
    }

    @GetMapping("/userLogin")
    public String enterAsUser(@ModelAttribute(USER_DTO) UserDTO userDTO){
        return USER_SIGN_IN_PAGE_PATH;
    }


    @GetMapping("/register")
    public String register(@ModelAttribute(USER_DTO) UserDTO userDTO){
        return USER_REGISTER_PAGE_PATH;
    }

}
