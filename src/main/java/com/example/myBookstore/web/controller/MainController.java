package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showWelcomePage(ModelMap model) {
        model.addAttribute("userName", userService.getLoggedinUserNamer());
        return "index";
    }
}
