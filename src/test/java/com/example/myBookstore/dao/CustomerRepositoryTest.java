package com.example.myBookstore.dao;

import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerInfo customerInfoInit1;
    private User userInit1;

    @BeforeEach
    void init() {
        userInit1 = new User();

        customerInfoInit1 = new CustomerInfo();
    }

    @Test
    @DisplayName("Should findByUser - Success")
    void findByUser() {
        entityManager.persist(userInit1);
        entityManager.flush();
        customerInfoInit1.setUser(userInit1);
        entityManager.persist(customerInfoInit1);
        entityManager.flush();

        CustomerInfo result = customerRepository.findByUser(userInit1);

        assertThat(result, is(customerInfoInit1));
    }

    @Test
    @DisplayName("Should findByUser - Not found")
    void findByUserNotFound() {
        entityManager.persist(userInit1);
        entityManager.flush();

        CustomerInfo result = customerRepository.findByUser(userInit1);

        assertThat(result, nullValue());
    }
}