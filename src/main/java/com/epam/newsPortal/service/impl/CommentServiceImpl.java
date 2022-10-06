package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.CommentDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;
import com.epam.newsPortal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentDAO.getComment(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Override
    public List<Comment> getAllCommentsToThisArticle(Article article) {
        return commentDAO.getAllCommentsToThisArticle(article);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDAO.deleteComment(comment);

    }

    @Override
    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }
}
