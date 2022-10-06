package com.epam.newsPortal.dao;
import com.epam.newsPortal.entity.User;
import java.util.List;

public interface UserDAO {

    void addUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    void deleteUser(User user);
    void updateUser(User user);
    User getUserByLoginAndPassword(String login, String password);

}
