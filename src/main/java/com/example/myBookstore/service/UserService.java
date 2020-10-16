package com.example.myBookstore.service;

import com.example.myBookstore.model.User;
import com.example.myBookstore.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
