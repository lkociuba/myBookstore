package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CataloqueServiceImpl;
import com.example.myBookstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookAddController.class)
class BookAddControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CataloqueServiceImpl bookService;

    @Test
    void bookAddDto() {
    }

    @Test
    void showBookAddSide() throws Exception {
        mockMvc.perform(get("/bookAdd")).andExpect(status().isOk());
    }

    @Test
    void addNewBook() {
    }
}