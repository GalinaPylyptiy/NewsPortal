package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.RoleDAO;
import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service

public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public Collection<Role> getListOfRoles() {
        return roleDAO.getListOfRoles();
    }

    @Override
    public void addRoleToAUser(User user, Role role) {
     roleDAO.addRoleToAUser(user,role);
    }

    @Override
    public void deleteRoleFromUser(User user, Role role) {
        roleDAO.deleteRoleFromUser(user, role);
    }
}
