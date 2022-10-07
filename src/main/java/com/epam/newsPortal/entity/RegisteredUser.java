package com.epam.newsPortal.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User implements Serializable {

    @OneToMany (mappedBy = "whoCreated")
    private List<Comment> comment;

    public RegisteredUser(String login, String password) {
        super(login, password);
    }
    public RegisteredUser() {
    }

}
