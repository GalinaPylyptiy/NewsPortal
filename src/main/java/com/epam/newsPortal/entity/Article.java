package com.epam.newsPortal.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String body;
    private LocalDate dayPosted;
    private LocalTime timePosted;
    @ManyToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

    public Article(){}

    public Article(Category category, String title, String body, LocalDate dayPosted, LocalTime timePosted, Editor editor) {
        this.category = category;
        this.title = title;
        this.body = body;
        this.dayPosted = dayPosted;
        this.timePosted = timePosted;
        this.editor = editor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public LocalDate getDayPosted() {
        return dayPosted;
    }

    public void setDayPosted(LocalDate dayPosted) {
        this.dayPosted = dayPosted;
    }

    public LocalTime getTimePosted() {
        return timePosted.withNano(0);
    }

    public void setTimePosted(LocalTime timePosted) {
        this.timePosted = timePosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(category, article.category) &&
                Objects.equals(title, article.title) &&
                Objects.equals(body, article.body) &&
                Objects.equals(dayPosted, article.dayPosted) &&
                Objects.equals(timePosted, article.timePosted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, body, dayPosted, timePosted);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dayPosted=" + dayPosted+
                ", timePosted=" + timePosted+
                '}';
    }
}
