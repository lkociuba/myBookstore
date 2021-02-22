package com.example.myBookstore.dao;

import com.example.myBookstore.entity.Role;
import com.example.myBookstore.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User userInit;

    @BeforeEach
    void init() {
        userInit = new User();
        userInit.setFirstName("Tola");
        userInit.setLastName("Nokka");
        userInit.setEmail("tola@email.com");
        userInit.setPassword("$2y$12$208Rt4ViYxpzhbcqyplmb.9VaVKd0OywYOq74iz0ZH1i2C3Zz3yY.");
        userInit.setRoles(new HashSet<Role>(Arrays.asList(new Role("ROLE_USER"))));
    }

    @Test
    @DisplayName("Should findByEmail Success")
    void findByEmail() {
        entityManager.persist(userInit);
        entityManager.flush();

        User result = userRepository.findByEmail("tola@email.com");

        assertThat(result.getEmail(), is(userInit.getEmail()));
    }

    @Test
    @DisplayName("Should findByEmail Not Found")
    void findByEmailNotFound() {
        User result = userRepository.findByEmail("tola@email.com");

        assertThat(result, nullValue());
    }
}