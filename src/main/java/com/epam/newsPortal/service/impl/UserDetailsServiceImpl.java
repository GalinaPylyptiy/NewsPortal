package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.UserDAO;
import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service

public class UserDetailsServiceImpl implements UserDetailsService, SecurityService {

    private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User
                (user.getLogin(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipalName = authentication.getName();
            return userDAO.getUserByLogin(currentPrincipalName);
        } else {
           throw new UsernameNotFoundException("The user is not found");
        }
    }
}
