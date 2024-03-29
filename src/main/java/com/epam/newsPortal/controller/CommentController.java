package com.epam.newsPortal.controller;

import com.epam.newsPortal.dto.CommentDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;
import com.epam.newsPortal.mapper.CommentMapper;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import static com.epam.newsPortal.constants.AttributeNamesConstants.COMMENT_DTO;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.NEW_COMMENT_PAGE_PATH;

@Controller

@RequestMapping("/comment")

public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;
    private final CommentMapper commentMapper;
    private static final String ARTICLE_ID = "articleId";

    @Autowired

    public CommentController(CommentService commentService,
                             ArticleService articleService,
                             CommentMapper commentMapper) {
        this.commentService = commentService;
        this.articleService = articleService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/new")
    public String addComment(@ModelAttribute(COMMENT_DTO)CommentDTO commentDTO,
                             @RequestParam(ARTICLE_ID) String articleId){

        Article article = articleService.getArticle(Long.parseLong(articleId));
        commentDTO.setArticle(article);
        return NEW_COMMENT_PAGE_PATH;
    }

    @PostMapping("/save")
    public String saveComment(@ModelAttribute(COMMENT_DTO)CommentDTO commentDTO,
                              @RequestParam(ARTICLE_ID) String articleId){
        Long id = Long.parseLong(articleId);
        commentMapper.setProperties(id, LocalDateTime.now(), commentDTO);
        Comment comment = commentMapper.toModel(commentDTO);
        commentService.addComment(comment);
        return "redirect:/article/" + articleId + "/show";
    }
}
