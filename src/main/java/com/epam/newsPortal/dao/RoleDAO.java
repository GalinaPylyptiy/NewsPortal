package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;

import java.util.Collection;

public interface RoleDAO {

    Role getRoleByName(String name);
    Role getRoleById(Long id);
    Collection <Role> getListOfRoles();
    void addRoleToAUser(User user, Role role);
    void deleteRoleFromUser(User user, Role role);
}
