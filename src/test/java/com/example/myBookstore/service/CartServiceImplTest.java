package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private Cart cartMock;

    @InjectMocks
    private CartServiceImpl cartService;

    private CartItem cartItemInit1;
    private CartItem cartItemInit2;
    private List<CartItem> cartItemListInit;

    @BeforeEach
    void init() {
        cartItemInit1 = new CartItem();
        cartItemInit2 = new CartItem();

        cartItemListInit = new ArrayList<>();
        cartItemListInit.add(cartItemInit1);
        cartItemListInit.add(cartItemInit2);
    }

    @Test
    @DisplayName("Should deleteCartItem - invoke cart.deleteCartItem")
    void deleteCartItem() {
        cartMock.deleteCartItem(1L);

        verify(cartMock, times(1)).deleteCartItem(Mockito.anyLong());
    }

    @Test
    @DisplayName("Should getCartItems - Success")
    void getCartItems() {
        given(cartMock.getCartItems()).willReturn(cartItemListInit);

        List<CartItem> result = cartService.getCartItems();

        assertThat(result, is(cartItemListInit));
    }

    @Test
    @DisplayName("Should addCartItem - invoke cart.addCartItem")
    void addCartItem() {
        cartMock.addCartItem(1L);

        verify(cartMock, times(1)).addCartItem(Mockito.anyLong());
    }

    @Test
    @DisplayName("Should increaseCartItemQuantity - invoke cart.increaseCartItemQuantity")
    void increaseCartItemQuantity() {
        cartMock.increaseCartItemQuantity(1L);

        verify(cartMock, times(1)).increaseCartItemQuantity(Mockito.anyLong());
    }

    @Test
    @DisplayName("Should decreaseCartItemQuantity - invoke cart.decreaseCartItemQuantity")
    void decreaseCartItemQuantity() {
        cartMock.decreaseCartItemQuantity(1L);

        verify(cartMock, times(1)).decreaseCartItemQuantity(Mockito.anyLong());
    }

    @Test
    @DisplayName("Should calculatedPrice - Success")
    void calculatedPrice() {
        given(cartMock.calculatedPrice()).willReturn(10.0);

        double result = cartService.calculatedPrice();

        assertThat(result, is(10.0));
    }

    @Test
    @DisplayName("SHould deleteCartItemsAfterSaveOrder - invoke cart.deleteCartItemsAfterSaveOrder")
    void deleteCartItemsAfterSaveOrder() {
        cartMock.deleteCartItemsAfterSaveOrder();

        verify(cartMock, times(1)).deleteCartItemsAfterSaveOrder();
    }
}