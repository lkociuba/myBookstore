package com.example.myBookstore.service;

import com.example.myBookstore.entity.User;
import com.example.myBookstore.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    Long getLoogedUserId();

    User findUser();

    String getLoggedinUserName();
}
