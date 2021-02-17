package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.UserService;
import com.example.myBookstore.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping  //ten niby z validation
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto){
                                     // BindingResult bindingResult){

        /*
        @Valid
        if (bindingResult.hasErrors()) {
            return "registration";
        }

         */

        userService.save(registrationDto);
        return "redirect:/registration?success";


    }

}

