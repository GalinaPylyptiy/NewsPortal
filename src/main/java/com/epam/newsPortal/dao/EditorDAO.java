package com.epam.newsPortal.dao;

import com.epam.newsPortal.entity.Editor;

public interface EditorDAO {
    void addEditor(Editor editor);
    Editor getEditor(Long id);
    Editor getEditorByLoginAndPassword(String login, String password);
}
