package com.epam.newsPortal.controller;

import com.epam.newsPortal.config.TestBeanConfig;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.entity.Editor;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import com.epam.newsPortal.service.EditorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexPageController.class)
@ContextConfiguration(classes = {TestBeanConfig.class})

class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;
    @MockBean
    EditorService editorService;
    @MockBean
    CategoryService categoryService;


    @Test
    void addArticle() throws Exception {
        List<Category> categoryList = categoryList();
        Editor editor = new Editor(1L, "sam_smith", "password", "Smith", "Sam" );
        given(categoryService.getAll()).willReturn(categoryList);
        given(editorService.getEditor(editor.getId())).willReturn(editor);

        this.mockMvc.perform(get("/article/addArticle")
                .param("editorId", String.valueOf(editor.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categoryList", categoryList))
                .andExpect(model().attribute("editor", editor))
                .andExpect(view().name("article/addArticle"));

    }

    @Test
    void saveArticle() throws Exception {
        Category category = categoryList().get(0);
        Editor editor = new Editor(1L, "sam_smith", "password", "Smith", "Sam" );
        Article article = new Article(1L, category, "Title", "New information");
        given(categoryService.getCategoryById(category.getId())).willReturn(category);
        given(editorService.getEditor(editor.getId())).willReturn(editor);
        doNothing().when(articleService).addArticle(article);
        articleService.addArticle(article);
        verify(articleService, times(1)).addArticle(article);

        this.mockMvc.perform(post("/article/save")
                .param("editorId", String.valueOf(editor.getId()))
                .param("categoryId", String.valueOf(category.getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("article/seeArticle"));

    }
    private List<Category> categoryList(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "Criminal news"));
        categoryList.add(new Category(2L,"Political news" ));
        return categoryList;
    }

}