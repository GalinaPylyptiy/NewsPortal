package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);
    Comment getComment(Long id);
    List<Comment> getAllComments();
    List<Comment> getAllCommentsToThisArticle(Article article);
    void deleteComment(Comment comment);
    void updateComment(Comment comment);
}
