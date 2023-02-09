package com.epam.newsPortal.dto;

import com.epam.newsPortal.entity.Role;

import java.util.Collection;

public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private Collection<Role> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
