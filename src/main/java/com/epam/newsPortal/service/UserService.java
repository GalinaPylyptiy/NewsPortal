package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.User;

import java.util.Collection;

public interface UserService {

    void addUser(User user);
    User getUser(Long id);
    Collection<User> getAllUsers();
    void deleteUser(User user);
    void updateUser(User user);
    User getUserByLoginAndPassword(String login, String password);
    User getUserByLogin(String login);
}
