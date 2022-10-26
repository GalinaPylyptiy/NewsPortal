package com.epam.newsPortal.controller;

import com.epam.newsPortal.dto.UserDTO;
import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.mapper.UserMapper;
import com.epam.newsPortal.service.RoleService;
import com.epam.newsPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static com.epam.newsPortal.constants.AttributeNamesConstants.ADMIN;
import static com.epam.newsPortal.constants.AttributeNamesConstants.ALL_USERS;
import static com.epam.newsPortal.constants.AttributeNamesConstants.EDITOR;
import static com.epam.newsPortal.constants.AttributeNamesConstants.LOGIN_EXISTS;
import static com.epam.newsPortal.constants.AttributeNamesConstants.NO_USER_FOUND;
import static com.epam.newsPortal.constants.AttributeNamesConstants.USER;
import static com.epam.newsPortal.constants.AttributeNamesConstants.USER_DTO;
import static com.epam.newsPortal.constants.AttributeNamesConstants.USER_ROLES;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.ADMIN_MAIN_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.EDITOR_MAIN_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_ADMIN_MAIN_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_INDEX_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_REGISTER_USER_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_USER_PROFILE_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_USER_LOGIN_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.USER_EDIT_PAGE_PATH;
import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.USER_PROFILE_PAGE_PATH;

@Controller

@RequestMapping("/user")

public class UserPageController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final HttpSession session;
    private static final String ID = "id";
    private static final String LOGIN_EXISTS_ERROR = "This login is already exists, create a new one";
    private static final String NO_USER_EXISTS_ERROR = "There is no user with this login found. Please, try again!";
    private static final String EDITOR_ROLE = "Editor";
    private static final String ADMIN_ROLE = "Admin";


    @Autowired
    public UserPageController(UserService userService,
                              RoleService roleService,
                              HttpSession session,
                              UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.roleService = roleService;
        this.session = session;
    }

    @GetMapping("")
    public String getProfilePage(Model model){
        User user = (User)session.getAttribute(USER);
        model.addAttribute(USER, user);
        model.addAttribute(USER_ROLES,user.getUserRoles());
        return  USER_PROFILE_PAGE_PATH;
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute(USER_DTO) UserDTO userDTO){
        User user;
        try{
            user = userService.getUserByLogin(userDTO.getLogin());
            if(user!=null){
                session.setAttribute(LOGIN_EXISTS, LOGIN_EXISTS_ERROR);
                return REDIRECT_TO_REGISTER_USER_PAGE;
            }
        } catch (NoResultException ex){
            userDTO.setUserRoles(userMapper.setUserRoles());
            user = userMapper.toModel(userDTO);
            userService.addUser(user);
        }
        return REDIRECT_TO_USER_LOGIN_PAGE;
    }

    @PostMapping("/signIn")
    public String enter(@ModelAttribute(USER_DTO) UserDTO userDTO){
        try {
            User registered = userService.getUserByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
            Collection<Role> userRoles = registered.getUserRoles();
            for (Role role : userRoles) {
                if ((role.getRoleName()).equals(EDITOR_ROLE)) {
                    session.setAttribute(USER, registered);
                    return EDITOR_MAIN_PAGE_PATH;
                }
                else if ((role.getRoleName()).equals(ADMIN_ROLE)) {
                    session.setAttribute(USER, registered);
                    return REDIRECT_TO_ADMIN_MAIN_PAGE;
                }
            }
            session.setAttribute(USER, registered);
            return REDIRECT_TO_INDEX_PAGE;
        }
        catch (NoResultException ex){

            session.setAttribute(NO_USER_FOUND, NO_USER_EXISTS_ERROR );

            return REDIRECT_TO_USER_LOGIN_PAGE ;
        }
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(ID)String id,
                           Model model){
        User user = userService.getUser(Long.parseLong(id));
        model.addAttribute(USER_DTO, userMapper.toDto(user));
        return USER_EDIT_PAGE_PATH;
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute(USER_DTO) UserDTO userDTO){
        User user = userMapper.toModel(userDTO);
        userMapper.setUserId(userDTO.getId(), user);
        userService.updateUser(user);
        return REDIRECT_TO_USER_PROFILE_PAGE;
    }

    @GetMapping("/logout")
    public String userLogout(){
        session.removeAttribute(USER);
        session.removeAttribute(NO_USER_FOUND);
        return REDIRECT_TO_INDEX_PAGE;
    }

    @GetMapping("/editor")
    public String getEditorPage(){
        return EDITOR_MAIN_PAGE_PATH ;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        Collection<User> allUsers = userService.getAllUsers();
        model.addAttribute(ALL_USERS, allUsers);
        model.addAttribute(EDITOR, roleService.getRoleByName(EDITOR_ROLE));
        model.addAttribute(ADMIN, roleService.getRoleByName(ADMIN_ROLE));
        return ADMIN_MAIN_PAGE_PATH;
    }

}
