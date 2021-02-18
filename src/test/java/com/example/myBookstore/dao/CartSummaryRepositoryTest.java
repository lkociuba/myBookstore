package com.example.myBookstore.dao;

import com.example.myBookstore.MyBookstoreApplication;
import com.example.myBookstore.entity.CartSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    void findByCartSummaryId() {
        entityManager.persist(summaryInit);
        entityManager.flush();

        CartSummary result = cartSummaryRepository.findByCartSummaryId(1L);
        logger.info("First course Retrieved {} ", result.getCartSummaryId());

        assertThat(result.getCartSummaryId(), is(summaryInit.getCartSummaryId()));

    }
}

