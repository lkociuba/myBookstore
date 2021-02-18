package com.example.myBookstore.service;

import com.example.myBookstore.entity.Role;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.dao.UserRepository;
import com.example.myBookstore.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String USER_EMAIL = "tola@email.com";

    @Mock
    private UserRepository userRepoMock;

    @InjectMocks
    private UserServiceImpl userService;

    private User userInit;
    private UserRegistrationDto userRegDTO;

    @BeforeEach
    void init() {
        userInit = new User();
        userInit.setFirstName("Tola");
        userInit.setLastName("Nokka");
        userInit.setEmail(USER_EMAIL);
        userInit.setPassword("$2y$12$208Rt4ViYxpzhbcqyplmb.9VaVKd0OywYOq74iz0ZH1i2C3Zz3yY.");
        userInit.setRoles(new HashSet<Role>(Arrays.asList(new Role("ROLE_USER"))));
    }

    @Test
    void loadUserByUsernameAndUserExists() {
        given(userRepoMock.findByEmail(Mockito.anyString())).willReturn(userInit);

        UserDetails loadedUser = userService.loadUserByUsername(USER_EMAIL);

        assertThat(loadedUser, notNullValue());
    }

    @Test
    void loadUserByUsernameAndUserIsNull() {
        given(userRepoMock.findByEmail(Mockito.anyString())).willReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(USER_EMAIL);
        });

    }


}