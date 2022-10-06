package com.epam.newsPortal.dto;

import com.epam.newsPortal.entity.Article;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CommentDTO {

    private Article article;
    private LocalDateTime whenPosted;

    public CommentDTO(){

    }

    public CommentDTO(Article article, LocalDateTime whenPosted) {
        this.article = article;
        this.whenPosted = whenPosted;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public LocalDateTime getWhenPosted() {
        return whenPosted;
    }

    public void setWhenPosted(LocalDateTime whenPosted) {
        this.whenPosted = whenPosted;
    }
}
