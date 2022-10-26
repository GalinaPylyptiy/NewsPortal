package com.epam.newsPortal.dto;

import com.epam.newsPortal.entity.Category;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ArticleDTO {

    private Category category;
    private LocalDateTime whenPosted;

    public ArticleDTO (){}

    public ArticleDTO(Category category, LocalDateTime whenPosted) {
        this.category = category;
        this.whenPosted = whenPosted;
    }

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
}
