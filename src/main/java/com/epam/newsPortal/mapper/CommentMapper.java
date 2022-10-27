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

    private ArticleService articleDAO;


    @Autowired
    public CommentMapper(ArticleService articleDAO) {
        this.articleDAO = articleDAO;
    }

    public void setProperties (Long articleId, LocalDateTime whenPosted,CommentDTO commentDTO) {
        Article article = articleDAO.getArticle(articleId);
        commentDTO.setArticle(article);
        commentDTO.setWhenPosted(whenPosted);
    }

    public Comment toModel(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setWhenCreated(commentDTO.getWhenCreated());
        comment.setArticle(commentDTO.getArticle());
        return comment;
    }
}
