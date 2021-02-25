package com.example.myBookstore.web;

import com.example.myBookstore.service.UserServiceImpl;
import com.example.myBookstore.web.controller.UserRegistrationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserRegistrationController.class)
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void userRegistrationDto() {
    }

    @Test
    void showRegistrationForm() {
    }


    @Test
    void registerUserAccount() {
    }
}