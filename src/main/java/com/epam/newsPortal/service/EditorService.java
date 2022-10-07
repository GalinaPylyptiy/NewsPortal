package com.epam.newsPortal.service;

import com.epam.newsPortal.entity.Editor;

public interface EditorService {
    void addEditor(Editor editor);
    Editor getEditorByLoginAndPassword(String login, String password);
    Editor getEditor(Long id);
}
