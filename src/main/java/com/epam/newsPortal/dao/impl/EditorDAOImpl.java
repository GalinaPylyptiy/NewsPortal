package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.EditorDAO;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.util.PasswordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


@Repository
@Component
public class EditorDAOImpl implements EditorDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addEditor(Editor editor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        editor.setPassword(PasswordHandler.reversePassword(editor.getPassword()));
        entityManager.persist(editor);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public Editor getEditor(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Editor editor = entityManager.find(Editor.class, id);
        entityManager.close();
        return editor;
    }

    @Override
    public Editor getEditorByLoginAndPassword(String login, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from Editor e where e.login = :login AND e.password=:password")
                .setParameter("login", login).setParameter("password", password);
        Editor editor = (Editor) query.getSingleResult();
        entityManager.close();
        return editor;
    }
}
