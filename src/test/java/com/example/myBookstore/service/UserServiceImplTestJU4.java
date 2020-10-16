package com.example.myBookstore.service;

import com.example.myBookstore.model.Role;
import com.example.myBookstore.model.User;
import com.example.myBookstore.repository.UserRepository;
import com.example.myBookstore.web.dto.UserRegistrationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTestJU4 {

    private static final String USER_EMAIL = "tola@email.com";
    private static final String USER_PASSWORD = "$2y$12$208Rt4ViYxpzhbcqyplmb.9VaVKd0OywYOq74iz0ZH1i2C3Zz3yY.";

    @Mock
    private UserRepository userRepoMock;

    @InjectMocks
    private UserServiceImpl userService;

    private User userInit;
    private UserRegistrationDto userRegDTO;

    @Before
    public void init(){
        userInit = new User("Tola", "Nokka", USER_EMAIL, USER_PASSWORD
                , new HashSet<Role>(Arrays.asList(new Role("ROLE_USER"))));

        userRegDTO = new UserRegistrationDto();
        userRegDTO.setFirstName("Mumi");
        userRegDTO.setLastName("Kipi");
        userRegDTO.setEmail("mumi@email.com");
        userRegDTO.setPassword("$2y$12$ITXT6PnmDMQQRAXjWYIxqeqrZ87WbAvyY4FQ/6KmLefgtW6hGFONG");
    }

    @Test
    public void loadUserByUsername_AndUserExists() {
       // Mockito.when(userRepoMock.findByEmail(Mockito.anyString())).thenReturn(userInit);
        given(userRepoMock.findByEmail(Mockito.anyString())).willReturn(userInit);

        assertNotNull(userService.loadUserByUsername(USER_EMAIL));

    }

    @Test (expected = UsernameNotFoundException.class)
    public void loadUserByUsername_AndUserIsNull() {   // - z tym nie działa - sprawdź; throws UsernameNotFoundException
        Mockito.when(userRepoMock.findByEmail(Mockito.anyString())).thenReturn(null);

        assertNull(userService.loadUserByUsername(USER_EMAIL));
    }


}