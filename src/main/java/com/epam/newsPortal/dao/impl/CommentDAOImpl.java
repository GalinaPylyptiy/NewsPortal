package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.CommentDAO;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;


@Repository

public class CommentDAOImpl implements CommentDAO {

    private EntityManagerFactory entityManagerFactory;
    private static final String ARTICLE = "article";

    @Autowired
    public CommentDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addComment(Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(comment);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public Comment getComment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.close();
        return comment;
    }

    @Override
    public Collection<Comment> getAllComments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Comment", Comment.class);
        Collection<Comment> comments = query.getResultList();
        entityManager.close();
        return comments;
    }

    @Override
    public Collection<Comment> getAllCommentsToThisArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select c from Comment c where c.article = :article order by c.whenCreated desc")
                .setParameter(ARTICLE, article);
        Collection<Comment> comments = query.getResultList();
        entityManager.close();
        return comments;
    }

    @Override
    public void deleteComment(Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(comment);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void updateComment(Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(comment);
        transaction.commit();
        entityManager.close();

    }
}
