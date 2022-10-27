package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;

import java.util.Collection;
import java.util.List;


public interface CommentDAO {

    void addComment(Comment comment);
    Comment getComment(Long id);
    Collection<Comment> getAllComments();
    Collection<Comment> getAllCommentsToThisArticle(Article article);
    void deleteComment(Comment comment);
    void updateComment(Comment comment);
}
