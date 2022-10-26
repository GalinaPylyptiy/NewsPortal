package com.epam.newsPortal.mapper;

import com.epam.newsPortal.dto.CommentDTO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;
import com.epam.newsPortal.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentDTO commentDTO;

    public CommentDTO setProperties (Long articleId, LocalDateTime whenPosted) {
        Article article = articleService.getArticle(articleId);
        commentDTO.setArticle(article);
        commentDTO.setWhenPosted(whenPosted);
        return commentDTO;
    }


    public Comment map(Comment comment, CommentDTO commentDTO){
        comment.setArticle(commentDTO.getArticle());
        comment.setWhenCreated(commentDTO.getWhenPosted());
        return comment;
    }
}
