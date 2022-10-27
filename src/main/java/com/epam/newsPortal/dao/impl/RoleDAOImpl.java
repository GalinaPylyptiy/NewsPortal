package com.epam.newsPortal.dao.impl;

import com.epam.newsPortal.dao.RoleDAO;
import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collection;

@Repository

public class RoleDAOImpl implements RoleDAO {

    private EntityManagerFactory entityManagerFactory;
    private static final String ADD_ROLE_TO_A_USER = "INSERT INTO user_role(user_id, role_id) VALUES(:user_id, :role_id)";
    private static final String DELETE_ROLE_FROM_USER = "DELETE FROM user_role WHERE user_id = :user_id AND role_id = :role_id";
    private static final String ROLE_ID = "role_id";
    private static final String ROLE_NAME = "roleName";
    private static final String USER_ID = "user_id";

    @Autowired
    public RoleDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Role getRoleByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select r from Role r where r.roleName = :roleName")
                .setParameter(ROLE_NAME, name);
        Role role = (Role) query.getSingleResult();
        entityManager.close();
        return role;
    }

    @Override
    public Role getRoleById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Role role = entityManager.find(Role.class, id);
        entityManager.close();
        return role;
    }

    @Override
    public Collection<Role> getListOfRoles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Role", Role.class);
        Collection<Role> roles = query.getResultList();
        entityManager.close();
        return roles;
    }

    @Override
    public void addRoleToAUser(User user, Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(ADD_ROLE_TO_A_USER);
        query.setParameter(USER_ID, user.getId()).setParameter(ROLE_ID, role.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteRoleFromUser(User user, Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(DELETE_ROLE_FROM_USER);
        query.setParameter(USER_ID, user.getId()).setParameter(ROLE_ID, role.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
