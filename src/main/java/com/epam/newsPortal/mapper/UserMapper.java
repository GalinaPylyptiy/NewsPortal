package com.epam.newsPortal.mapper;

import com.epam.newsPortal.dao.RoleDAO;
import com.epam.newsPortal.dto.UserDTO;
import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component

public class UserMapper {

    private RoleDAO roleDAO;
    private final String userRole = "USER";

    @Autowired
    public UserMapper(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public User toModel(UserDTO userDTO){
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setUserRoles(userDTO.getUserRoles());
        return user;
    }

    public UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRoles(user.getUserRoles());
        return userDTO;
    }

    public void setUserId(Long id, User user){
        user.setId(id);
    }

    public Collection<Role> setUserRoles(){
        Role role = roleDAO.getRoleByName(userRole);
        return Collections.singleton(role);
    }

}
