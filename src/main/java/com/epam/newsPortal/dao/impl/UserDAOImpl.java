package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.UserDAO;
import com.epam.newsPortal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;


@Repository

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory entityManagerFactory;
    private BCryptPasswordEncoder passwordEncoder;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Autowired
    public UserDAOImpl(EntityManagerFactory entityManagerFactory, BCryptPasswordEncoder passwordEncoder) {
        this.entityManagerFactory = entityManagerFactory;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public User getUser(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from User", User.class);
        Collection<User> users = query.getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public void deleteUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
        entityManager.close();

    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User oldUserData = entityManager.find(User.class, user.getId());
        user.setUserRoles(oldUserData.getUserRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select u from User u where u.login = :login AND u.password = :password")
                .setParameter(LOGIN, login).setParameter(PASSWORD,passwordEncoder.encode(password) );
        User user = (User) query.getSingleResult();
        entityManager.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select u from User u where u.login = :login")
                .setParameter(LOGIN, login);
        User user = (User) query.getSingleResult();
        entityManager.close();
        return user;
    }
}
