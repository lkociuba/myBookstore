package com.example.myBookstore.dao;

import com.example.myBookstore.entity.CartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartItemRepository cartItemRepository;

    private CartItem cartItemInit1 = new CartItem();

    @Test
    @DisplayName("Should findByCartItemId - Success")
    void findByCartItemId() {
        entityManager.persist(cartItemInit1);
        entityManager.flush();

        CartItem result = cartItemRepository.findByCartItemId(1L);

        assertThat(result.getCartItemId(), is(cartItemInit1.getCartItemId()));
    }

    @Test
    @DisplayName("Should findByCartItemId - Not found")
    void findByCartItemIdNotFound() {
        CartItem result = cartItemRepository.findByCartItemId(1L);

        assertThat(result, nullValue());
    }
}