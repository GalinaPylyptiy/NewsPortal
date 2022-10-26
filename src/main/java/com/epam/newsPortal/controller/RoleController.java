package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.Role;
import com.epam.newsPortal.entity.User;
import com.epam.newsPortal.service.RoleService;
import com.epam.newsPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.epam.newsPortal.constants.HtmlPagesPathsConstants.REDIRECT_TO_ADMIN_MAIN_PAGE;

@Controller
@RequestMapping("/role")

public class RoleController {

    private final RoleService roleService;
    private final UserService userService;
    private static final String ROLE = "role";
    private static final String USER_ID = "userId";

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addNewRoleForUser(@RequestParam(ROLE) String role,
                                    @RequestParam(USER_ID) Long userId){
        Role userRole = roleService.getRoleByName(role);
        User user = userService.getUser(userId);
        roleService.addRoleToAUser(user,userRole);
        return REDIRECT_TO_ADMIN_MAIN_PAGE;
    }

    @PostMapping("/delete")
    public String deleteRoleFromUser(@RequestParam(ROLE) String role,
                                     @RequestParam(USER_ID) Long userId){
        Role userRole = roleService.getRoleByName(role);
        User user = userService.getUser(userId);
        roleService.deleteRoleFromUser(user,userRole);
        return REDIRECT_TO_ADMIN_MAIN_PAGE;
    }
}
