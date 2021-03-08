package com.example.myBookstore.model;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.entity.Book;
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
class CartTest {

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
        userInit1.setId(1L);

        cartItemInit1 = new CartItem();
        cartItemInit1.setUser(userInit1);

        cartItemInit2 = new CartItem();
        cartItemInit2.setUser(userInit1);

        cartItemListInit = new ArrayList<>();
        cartItemListInit.add(cartItemInit1);
        cartItemListInit.add(cartItemInit2);
    }

    @Test
    @DisplayName("Should getCartItems - Success")
    void getCartItems() {
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        List<CartItem> result = cart.getCartItems();

        assertThat(result.size(), is(2));
    }

    @Test
    @DisplayName("Should getCartItems - Empty list (no CartItem related with User)")
    void getCartItemsEmpty() {
        given(userServiceMock.getLoogedUserId()).willReturn(2L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        List<CartItem> result = cart.getCartItems();

        assertThat(result.size(), is(0));
    }

    @Test
    void addCartItem() {

        given(userServiceMock.findUser()).willReturn(userInit1);

    }

    @Test
    @DisplayName("Should deleteCartItem - Success")
    void deleteCartItem() {
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItemInit1);

        cart.deleteCartItem(1L);

        verify(cartItemRepoMock, times(1)).delete(cartItemInit1);
    }

    @Test
    @DisplayName("Should deleteCartItem - Verify cartItemRepository.delete no invocation (CartItem not found")
    void deleteCartItemNotDeleted() {
        cart.deleteCartItem(1L);

        verify(cartItemRepoMock, times(0)).delete(cartItemInit1);
    }


    @Test
    @DisplayName("Should increaseCartItemQuantity - Increase cartItem quantity from 1 to 2")
    void increaseCartItemQuantity() {
        cartItemInit1.setQuantity(1);
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItemInit1);

        cart.increaseCartItemQuantity(1L);

        assertThat(cartItemInit1.getQuantity(), is(2));
    }

    @Test
    @DisplayName("Should increaseCartItemQuantity - Pass Null value")
    void increaseCartItemQuantityNotIncreased() {
        cartItemInit1.setQuantity(1);

        cart.increaseCartItemQuantity(null);

        assertThat(cartItemInit1.getQuantity(), is(1));
        verify(cartItemRepoMock, times(0)).save(cartItemInit1);
    }

    @Test
    @DisplayName("Should increaseCartItemQuantity - Decrease cartItem quantity from 2 to 1")
    void decreaseCartItemQuantity() {
        cartItemInit1.setQuantity(2);
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItemInit1);

        cart.decreaseCartItemQuantity(1L);

        assertThat(cartItemInit1.getQuantity(), is(1));
    }

    @Test
    @DisplayName("Should increaseCartItemQuantity - Pass Null value")
    void decreaseCartItemQuantityNotIncreased() {
        cartItemInit1.setQuantity(2);

        cart.decreaseCartItemQuantity(null);

        verify(cartItemRepoMock, times(0)).save(cartItemInit1);
    }

    @Test
    @DisplayName("Should increaseCartItemQuantity - Quantity 0, delete cartItem")
    void decreaseCartItemQuantityDelete() {
        cartItemInit1.setQuantity(1);
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItemInit1);

        cart.decreaseCartItemQuantity(1L);

        verify(cartItemRepoMock, times(1)).delete(cartItemInit1);
    }

    @Test
    @DisplayName("Should calculatedPrice - Normal multiplication (result 20)")
    void calculatedPrice() {
        Book book1 = new Book();
        book1.setPrice(10);
        cartItemInit1.setBook(book1);
        cartItemInit1.setQuantity(1);
        cartItemInit2.setBook(book1);
        cartItemInit2.setQuantity(1);
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        double result = cart.calculatedPrice();

        assertThat(result, is(20.0));
    }

    @Test
    @DisplayName("Should calculatedPrice - Pass Null value (result 10)")
    void calculatedPricePassNullValue() {
        Book book1 = new Book();
        book1.setPrice(10);
        cartItemInit1.setBook(book1);
        cartItemInit2.setBook(book1);
        cartItemInit2.setQuantity(1);
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        double result = cart.calculatedPrice();

        assertThat(result, is(10.0));
    }

    @Test
    @DisplayName("Should calculatedPrice - Pass book price 0 (result 0)")
    void calculatedPricePass0() {
        Book book1 = new Book();
        book1.setPrice(0);
        cartItemInit1.setBook(book1);
        cartItemInit1.setQuantity(1);
        cartItemInit2.setBook(book1);
        cartItemInit2.setQuantity(1);
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        double result = cart.calculatedPrice();

        assertThat(result, is(0.0));
    }

    @Test
    @DisplayName("Should deleteCartItemsAfterSaveOrder - Success")
    void deleteCartItemsAfterSaveOrder() {
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(cartItemRepoMock.findAll()).willReturn(cartItemListInit);

        cart.deleteCartItemsAfterSaveOrder();

        verify(cartItemRepoMock, times(1)).delete(cartItemInit1);
    }

    @Test
    @DisplayName("Should deleteCartItemsAfterSaveOrder - Verify cartItemRepository.delete no invocation (CartItem not found")
    void deleteCartItemsAfterSaveOrderNotDeleted() {
        cart.deleteCartItemsAfterSaveOrder();

        verify(cartItemRepoMock, times(0)).delete(cartItemInit1);
    }
}