package com.epam.newsPortal.dto;

import com.epam.newsPortal.entity.Category;
import java.time.LocalDateTime;

public class ArticleDTO {

    private Long id;
    private Category category;
    private LocalDateTime whenPosted;
    private String title;
    private String body;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getWhenPosted() {
        return whenPosted;
    }

    public void setWhenPosted(LocalDateTime whenPosted) {
        this.whenPosted = whenPosted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
