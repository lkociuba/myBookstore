package com.example.myBookstore.model;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.service.CataloqueServiceImpl;
import com.example.myBookstore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CartTest {

    @Autowired
    private TestEntityManager entityManager;

    @Mock
    private CartItemRepository cartItemRepoMock;

    @Mock
    private CataloqueServiceImpl cataloqueServiceMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @InjectMocks
    private Cart cart;

    private User userInit1;
    private CartItem cartItemInit1;
    private CartItem cartItemInit2;
    private List<CartItem> cartItemListInit;

    @BeforeEach
    void init() {
        userInit1 = new User();
        cartItemInit1 = new CartItem();
        cartItemInit2 = new CartItem();
        cartItemListInit = new ArrayList<>();
        cartItemListInit.add(cartItemInit1);
        cartItemListInit.add(cartItemInit2);
    }

    @Test
    @DisplayName("Should getCartItems - Success")
    void getCartItems() {
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);
        entityManager.persist(userInit1);
        entityManager.flush();
        cartItemInit1.setUser(userInit1);
        cartItemInit2.setUser(userInit1);

        List<CartItem> result = cart.getCartItems();

        assertThat(result, is(cartItemListInit));
    }

    @Test
    @DisplayName("Should getCartItems - Empty list (no CartItem related with User)")
    void getCartItemsEmpty() {
        given(userServiceMock.getLoogedUserId()).willReturn(2L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);
        entityManager.persist(userInit1);
        entityManager.flush();
        cartItemInit1.setUser(userInit1);
        cartItemInit2.setUser(userInit1);

        List<CartItem> result = cart.getCartItems();

        assertThat(result.size(), is(0));
    }

    @Test
    void addCartItem() {
    }

    @Test
    void deleteCartItem() {
    }

    @Test
    void increaseCartItemQuantity() {
    }

    @Test
    void decreaseCartItemQuantity() {
    }

    @Test
    void calculatedPrice() {
    }

    @Test
    void deleteCartItemsAfterSaveOrder() {
    }
}