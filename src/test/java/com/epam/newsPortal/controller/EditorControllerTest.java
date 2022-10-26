package com.epam.newsPortal.controller;

import com.epam.newsPortal.config.TestBeanConfig;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.EditorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EditorController.class)
@ContextConfiguration(classes = {TestBeanConfig.class})

class EditorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean(name = "editorService")
    private EditorService editorService;


        @Test
    void getEditorPage() throws Exception {
      List <Editor> editorList = new ArrayList<>();
      Editor editor = getEditor();
      editorList.add(editor);
      given(editorService.getEditorByLoginAndPassword(editor.getLogin(), editor.getPassword()))
              .willReturn(editorList.get(0));

      this.mockMvc.perform(post("/editor/signIn")
              .param("login","john_smith" )
              .param("password","my_password" ))
              .andExpect(status().isOk())
              .andExpect(model().attribute("editor", editor))
      .andExpect(view().name("editor/mainPage"));

    }
    private Editor getEditor(){
        Editor editor = new Editor();
        editor.setId(1L);
        editor.setFirstName("John");
        editor.setLastName("Smith");
        editor.setLogin("john_smith");
        editor.setPassword("my_password");
        return editor;
    }
}