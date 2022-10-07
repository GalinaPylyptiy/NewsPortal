package com.epam.newsPortal.service.impl;

import com.epam.newsPortal.dao.EditorDAO;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EditorServiceImpl implements EditorService {
    private EditorDAO editorDAO;

    @Autowired
    public EditorServiceImpl(EditorDAO editorDAO) {
        this.editorDAO = editorDAO;
    }

    @Override
    public void addEditor(Editor editor) {
        editorDAO.addEditor(editor);

    }

    @Override
    public Editor getEditorByLoginAndPassword(String login, String password) {
        return editorDAO.getEditorByLoginAndPassword(login, password);
    }

    @Override
    public Editor getEditor(Long id) {
        return editorDAO.getEditor(id);
    }
}
