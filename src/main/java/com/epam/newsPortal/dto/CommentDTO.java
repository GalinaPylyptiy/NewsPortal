package com.epam.newsPortal.dto;

import com.epam.newsPortal.entity.Article;
import java.time.LocalDateTime;


public class CommentDTO {

    private Long id;
    private String content;
    private Article article;
    private LocalDateTime whenCreated;

    public CommentDTO(){

    }

    public CommentDTO(Article article, LocalDateTime whenCreated) {
        this.article = article;
        this.whenCreated = whenCreated;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public LocalDateTime getWhenPosted() {
        return whenCreated;
    }

    public void setWhenPosted(LocalDateTime whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(LocalDateTime whenCreated) {
        this.whenCreated = whenCreated;
    }
}
