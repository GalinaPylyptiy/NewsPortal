package com.epam.newsPortal.controller;

import com.epam.newsPortal.constants.ErrorMessageConstants;
import com.epam.newsPortal.dto.CommentDTO;
import com.epam.newsPortal.entity.Comment;
import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.mapper.CommentMapper;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ARTICLE_ID;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.COMMENT;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ERROR;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.USER;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.COMMENT_ERROR;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.COMMENT_NEW;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT;

@Controller
@RequestMapping("/comment")

public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/new")
    public String addComment(@ModelAttribute(COMMENT)Comment comment,
                             @RequestParam(ARTICLE_ID) String articleId,
                             HttpSession session, Model model){
        comment.setArticle(articleService.getArticle(Long.parseLong(articleId)));
        User user =(User) session.getAttribute(USER);
        if(user==null){
            model.addAttribute(ERROR, ErrorMessageConstants.COMMENT_ERROR);
           return COMMENT_ERROR ;
        }
        return COMMENT_NEW;
    }

    @PostMapping("/save")
    public String saveComment(@ModelAttribute(COMMENT)Comment comment,
                              @RequestParam(ARTICLE_ID) String articleId){
        CommentDTO commentDTO = commentMapper.setProperties(Long.parseLong(articleId), LocalDateTime.now());
        commentMapper.map(comment, commentDTO);
        commentService.addComment(comment);
        return REDIRECT;
    }
}
