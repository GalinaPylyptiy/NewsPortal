package com.epam.newsPortal.controller;

import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editor")
public class EditorController {
    private EditorService editorService;

    @Autowired
    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @PostMapping("/signIn")
    public String getEditorPage(@RequestParam("login") String login,
                                @RequestParam("password") String password,
                                Model model){
       Editor editor = editorService.getEditorByLoginAndPassword(login, password);
       model.addAttribute("editor", editor);
       return "editor/mainPage";
    }

}
