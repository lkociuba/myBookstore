package com.example.myBookstore.dao;

import com.example.myBookstore.MyBookstoreApplication;
import com.example.myBookstore.entity.CartSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
class CartSummaryRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(MyBookstoreApplication.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartSummaryRepository cartSummaryRepository;

    private CartSummary summaryInit;

    @BeforeEach
    void init() {
        summaryInit = new CartSummary();
    }

    @Test
    @DisplayName("Should findByCartSummaryId Success")
    void findByCartSummaryId() {
        entityManager.persist(summaryInit);
        entityManager.flush();

        CartSummary result = cartSummaryRepository.findByCartSummaryId(1L);

        assertThat(result.getCartSummaryId(), is(summaryInit.getCartSummaryId()));
    }

    @Test
    @DisplayName("Should findByCartSummaryId Not Found")
    void findByCartSummaryIdNotFound() {
        CartSummary result = cartSummaryRepository.findByCartSummaryId(1L);

        assertThat(result, nullValue());
    }
}

