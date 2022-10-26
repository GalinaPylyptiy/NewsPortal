package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.EDITOR;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.ERROR;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.LOGIN;
import static com.epam.newsPortal.constants.AttributeAndVariableNamesConstants.PASSWORD;
import static com.epam.newsPortal.constants.ErrorMessageConstants.EDITOR_LOGIN_ERROR;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.EDITOR_ERROR_PAGE;
import static com.epam.newsPortal.constants.HtmlPagesDirectoryConstants.EDITOR_MAIN_PAGE;

@Controller
@RequestMapping(EDITOR)

public class EditorController {

    private EditorService editorService;

    @Autowired
    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @PostMapping("/signIn")
    public String getEditorPage(@RequestParam(LOGIN) String login,
                                @RequestParam(PASSWORD) String password,
                                Model model){
        try {
            Editor editor = editorService.getEditorByLoginAndPassword(login, password);
            model.addAttribute(EDITOR, editor);
        }
        catch (Exception e){
            model.addAttribute(ERROR, EDITOR_LOGIN_ERROR);
            return EDITOR_ERROR_PAGE;
        }
       return EDITOR_MAIN_PAGE;
    }

}
