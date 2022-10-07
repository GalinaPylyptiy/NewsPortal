package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.EditorDAO;
import com.epam.newsPortal.entity.Editor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;


@Repository
@Component
public class EditorDAOImpl implements EditorDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public EditorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEditor(Editor editor) {
        Session session = sessionFactory.openSession();
        session.save(editor);
        session.close();
    }

    @Override
    public Editor getEditor(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Editor.class, id);
    }

    @Override
    public Editor getEditorByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("select*from editor where login = :login AND password=:password")
                .setParameter("login", login).setParameter("password", password).addEntity(Editor.class);
        Editor editor = (Editor) query.getSingleResult();
        session.close();
        return editor;
    }
}
