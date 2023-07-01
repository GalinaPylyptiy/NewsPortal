package com.epam.newsPortal.controller;

import com.epam.newsPortal.dto.ArticleDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.entity.Comment;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import com.epam.newsPortal.mapper.ArticleMapper;
import com.epam.newsPortal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.epam.newsPortal.constants.AttributeNamesConstants.ARTICLES;
import static com.epam.newsPortal.constants.AttributeNamesConstants.ARTICLE_DTO;
import static com.epam.newsPortal.constants.AttributeNamesConstants.CATEGORY_LIST;
import static com.epam.newsPortal.constants.AttributeNamesConstants.COMMENTS;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.ADD_NEW_ARTICLE_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.ARTICLE_SHOW_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.EDITOR_MAIN_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.EDIT_ARTICLE_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.INDEX_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_ARTICLE_LIST_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.SEE_NEW_ARTICLE_PAGE_PATH;

@Controller

@RequestMapping(value = "/article", produces = "text/plain;charset=UTF-8")

public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final ArticleMapper articleMapper;
    private final CommentService commentService;
    private static final String CATEGORY_ID = "categoryId";
    private static final String ID = "id";

    @Autowired

    public ArticleController(ArticleService articleService,
                             CategoryService categoryService,
                             ArticleMapper articleMapper,
                             CommentService commentService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.articleMapper = articleMapper;
        this.commentService = commentService;
    }

    @GetMapping("")
    public String showAllArticles(Model model){
        model.addAttribute(ARTICLES, articleService.getAllArticles());
        return EDITOR_MAIN_PAGE_PATH;
    }

    @GetMapping("/category")
    public String getArticlesByCategory( @RequestParam(CATEGORY_ID) String categoryId,
                                         Model model ) {

        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        model.addAttribute(ARTICLES,articleService.getArticlesByCategory(category));
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return INDEX_PAGE_PATH;
    }

    @GetMapping("/new")
    public String addArticle(@ModelAttribute(ARTICLE_DTO)ArticleDTO articleDTO,
                             Model model){
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return ADD_NEW_ARTICLE_PAGE_PATH ;
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute(ARTICLE_DTO)ArticleDTO articleDTO,
                              @RequestParam(CATEGORY_ID) String categoryId){

        articleMapper.setProperties(Long.parseLong(categoryId), LocalDateTime.now(), articleDTO);
        Article article = articleMapper.toModel(articleDTO);
        articleService.addArticle(article);
        return SEE_NEW_ARTICLE_PAGE_PATH;
    }

    @GetMapping("/{id}/edit")
    public String editArticle(@PathVariable(ID) String id,
                              Model model){
        Article article = articleService.getArticle(Long.parseLong(id));
        model.addAttribute(ARTICLE_DTO, articleMapper.toArticleDTO(article));
        model.addAttribute(CATEGORY_LIST, categoryService.getAll());
        return EDIT_ARTICLE_PAGE_PATH;
    }

    @PostMapping("/edit")
    public String saveUpdated(@ModelAttribute(ARTICLE_DTO)ArticleDTO articleDTO,
                              @RequestParam (CATEGORY_ID) String categoryId){

       articleMapper.setProperties(Long.parseLong(categoryId), articleDTO.getWhenPosted(), articleDTO);
       Article article = articleMapper.toModel(articleDTO);
       articleMapper.setArticleId(article, articleDTO.getId());
       articleService.updateArticle(article);
       return REDIRECT_TO_ARTICLE_LIST_PAGE_PATH;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteArticle(@PathVariable(ID) String id){
        Article article  = articleService.getArticle(Long.parseLong(id));
        articleService.deleteArticle(article);
        return REDIRECT_TO_ARTICLE_LIST_PAGE_PATH;
    }


    @GetMapping("/{id}/show")
    public String showArticle(@PathVariable(ID) String articleId,
                              Model model){
        Article article = articleService.getArticle(Long.parseLong(articleId));
        Collection<Comment> comments = commentService.getAllCommentsToThisArticle(article);
        model.addAttribute(ARTICLE_DTO, articleMapper.toArticleDTO(article));
        model.addAttribute(COMMENTS, comments);
        return ARTICLE_SHOW_PAGE_PATH;
    }
}
