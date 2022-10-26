package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.config.TestBeanConfig;
import com.epam.newsPortal.dao.EditorDAO;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.EditorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
@SpringBootTest

class EditorServiceImplTest {
    @Autowired
    private EditorService editorService;

    @MockBean
    private EditorDAO editorDAO;

    @Test
    void addEditorShouldAddEditorToDB() {
        Editor editor = getEditor();
        doNothing().when(editorDAO).addEditor(editor) ;
        editorDAO.addEditor(editor);
        verify(editorDAO, times(1)).addEditor(editor);
        Assertions.assertNotNull(editor);
    }

    @Test
    void getEditorByLoginAndPasswordShouldReturnOneEditor() {
      Editor editor= getEditor();
      doReturn(editor).when(editorDAO).getEditorByLoginAndPassword(editor.getLogin(), editor.getPassword());
      Editor returnedEditor = editorService.getEditorByLoginAndPassword(editor.getLogin(), editor.getPassword());
      Assertions.assertNotNull(returnedEditor);
      Assertions.assertEquals(returnedEditor, editor);
    }

    @Test
    void testGetEditor() {
        doReturn(getEditor()).when(editorDAO).getEditor(1L);
        Editor editor = editorService.getEditor(1L);
        Assertions.assertNotNull(editor);
        Assertions.assertEquals(editor, getEditor());
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