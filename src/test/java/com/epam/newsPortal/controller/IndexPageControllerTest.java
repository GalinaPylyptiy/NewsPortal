package com.epam.newsPortal.controller;

import com.epam.newsPortal.config.TestBeanConfig;
import com.epam.newsPortal.entity.Article;
import com.epam.newsPortal.entity.Category;
import com.epam.newsPortal.service.ArticleService;
import com.epam.newsPortal.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexPageController.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
class IndexPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "categoryService")
    private CategoryService categoryService;

    @MockBean(name = "articleService")
    private ArticleService articleService;

    @Test
    void getIndexPage() throws Exception {
        List<Category> categoryList = categoryList();
        List<Article> articleList = articleList();
        given(categoryService.getAll()).willReturn(categoryList);
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categoryList", categoryList))
                .andExpect(view().name("/indexPage"));

    }

    @Test
    void enterAsEditor() throws Exception {
        this.mockMvc.perform(get("/editorLogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("editor/signIn"));
    }

    private List <Category>categoryList(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "Criminal news"));
        categoryList.add(new Category(2L,"Political news" ));
        return categoryList;
    }

    private List<Article> articleList(){
      List<Article> articleList = new ArrayList<>();
      Category category = categoryList().get(0);
      Article article = new Article(1L, category, "Title", "New information");
      article.setWhenPosted(LocalDateTime.now());
      articleList.add(article);
      return articleList;
    }
}