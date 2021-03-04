package com.example.myBookstore.web.controller;

import com.example.myBookstore.entity.User;
import com.example.myBookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showWelcomePage(ModelMap model, Principal principal, Authentication authentication) {
        model.addAttribute("userName", getLoggedinUserNamer());
        model.addAttribute("principalUserName", principal.getName());
        model.addAttribute("atribute", authentication.getPrincipal());

        model.addAttribute("userId", userService.getLoogedUserId());

        return "index";
    }

    private String getLoggedinUserNamer() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }


}
