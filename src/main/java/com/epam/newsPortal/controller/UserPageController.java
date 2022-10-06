package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ID;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.USER;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT_USER;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.REDIRECT_USER_LOGIN;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.USER_EDIT;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.USER_PROFILE;

@Controller
@RequestMapping("/user")

public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/new")
    public String addUser(@ModelAttribute(USER) User user){
          userService.addUser(user);
         return REDIRECT_USER_LOGIN;
    }

    @PostMapping("/signIn")
    public String enter(@ModelAttribute(USER) User user){
        User registered = userService.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        session.setAttribute(USER, registered);
        return REDIRECT;
    }

    @GetMapping("")
    public String getProfilePage(Model model){
       model.addAttribute(USER, session.getAttribute(USER));
       return  USER_PROFILE;
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(ID)Long id, @ModelAttribute(USER) User user){
        user = userService.getUser(id);
        return USER_EDIT;
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute(USER) User user){
        userService.updateUser(user);
        return REDIRECT_USER;
    }

    @GetMapping("/logout")
    public String userLogout(){
        session.removeAttribute(USER);
        return REDIRECT;
    }

}
