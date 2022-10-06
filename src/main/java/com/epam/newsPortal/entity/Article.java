package com.epam.newsPortal.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
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
    private LocalDateTime whenPosted;
    @OneToMany(mappedBy = "article")
    private Collection <Comment> comments;

    public Article(){ }

    public Article(Long id, Category category, String title, String body) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.body = body;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
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

    public LocalDateTime getWhenPosted() {
        return whenPosted.withNano(0);
    }

    public void setWhenPosted(LocalDateTime whenPosted) {
        this.whenPosted = whenPosted;
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
                Objects.equals(whenPosted, article.whenPosted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, body, whenPosted);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", when Posted=" + whenPosted+
                '}';
    }
}
