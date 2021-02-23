package com.example.myBookstore.service;

import com.example.myBookstore.dao.CartSummaryRepository;
import com.example.myBookstore.entity.CartSummary;
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
class CartSummaryServiceImplTest {

    @Mock
    private CartSummaryRepository cartSummaryRepoMock;

    @InjectMocks
    private CartSummaryServiceImpl cartSummaryService;

    private CartSummary cartSummary1;
    private CartSummary cartSummary2;
    private List<CartSummary> cartSummaryList;

    @BeforeEach
    void init() {
        cartSummary1 = new CartSummary();

        cartSummary2 = new CartSummary();

        cartSummaryList = new ArrayList<>();
        cartSummaryList.add(cartSummary1);
        cartSummaryList.add(cartSummary2);
    }

    @Test
    @DisplayName("Should findAllCartSummary - Success")
    void findAllCartSummary() {
        given(cartSummaryRepoMock.findAll()).willReturn(cartSummaryList);

        List<CartSummary> result = cartSummaryService.findAllCartSummary();

        assertThat(result.size(), is(2));
        assertThat(result, equalTo(cartSummaryList));
    }

    @Test
    @DisplayName("Should findAllCartSummary - List is empty")
    void findAllCartSummaryIsEmpty() {
        List<CartSummary> result = cartSummaryService.findAllCartSummary();

        assertThat(result.size(), is(0));
    }

    @Test
    @DisplayName("Should findCartSummaryById - Success")
    void findCartSummaryById() {
        given(cartSummaryRepoMock.findByCartSummaryId(Mockito.anyLong())).willReturn(cartSummary1);

        CartSummary result = cartSummaryService.findCartSummaryById(1L);

        assertThat(result, equalTo(cartSummary1));
    }

    @Test
    @DisplayName("Should findCartSummaryById - Not found")
    void findCartSummaryByIdNotFound() {
        CartSummary result = cartSummaryService.findCartSummaryById(1L);

        assertThat(result, nullValue());
    }

    @Test
    @DisplayName("Should deleteCartItemById - Success")
    void deleteCartItemById() {
        given(cartSummaryRepoMock.save(Mockito.anyObject())).willReturn(new CartSummary());

        Long cartSummaryId = 1L;
        Long cartItemId = 1L;

        cartSummaryService.deleteCartItemById(cartSummaryId, cartItemId);

        verify(cartSummaryRepoMock, times(1));

        //to TO
    }
}