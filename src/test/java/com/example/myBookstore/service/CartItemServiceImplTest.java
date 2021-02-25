package com.example.myBookstore.service;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartItemServiceImplTest {

    @Mock
    private CartItemRepository cartItemRepoMock;

    @Mock
    private CartSummaryService cartSummaryServiceMock;

    @Mock
    private BookService bookServiceMock;

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    private CartItem cartItem1;
    private CartItem cartItem2;
    private List<CartItem> cartItemList;

    @BeforeEach
    void init() {
        cartItem1 = new CartItem();
        cartItem1.setQuantity(1);

        cartItem2 = new CartItem();
        cartItem2.setQuantity(2);

        cartItemList = new ArrayList<>();
        cartItemList.add(cartItem1);
        cartItemList.add(cartItem2);
    }


    @Test
    @DisplayName("Sould findAllCartItems - Success")
    void findAllCartItems() {
        given(cartItemRepoMock.findAll()).willReturn(cartItemList);

        List<CartItem> result = cartItemService.findAllCartItems();

        assertThat(result.size(), is(2));
        assertThat(result, equalTo(cartItemList));
    }

    @Test
    @DisplayName("Sould findAllCartItems - List is empty")
    void findAllCartItemsIsEmpty() {
        List<CartItem> result = cartItemService.findAllCartItems();

        assertThat(result.size(), is(0));
    }


    @Test
    @DisplayName("Should deleteCartItem - Verify CartItemRepository.delete invocation Success")
    void deleteCartItem() {
        cartItemService.deleteCartItem(cartItem1);
        cartItemService.deleteCartItem(cartItem1);

        verify(cartItemRepoMock, times(2)).delete(cartItem1);
    }

    @Test
    @DisplayName("Should findByCartItemId - Success")
    void findByCartItemId() {
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItem1);

        CartItem result = cartItemService.findByCartItemId(1L);

        assertThat(result, equalTo(cartItem1));
    }

    @Test
    @DisplayName("Should findByCartItemId - Not found")
    void findByCartItemIdNotFound() {
        CartItem result = cartItemService.findByCartItemId(1L);

        assertThat(result, is(nullValue()));
    }

    @Test
    @DisplayName("Should createCartItemWithBook - Success")
    void createCartItemWithBook() {
        Book book = new Book();

        assertThat(cartItemService.createCartItemWithBook(book).getBook(), equalTo(book));
    }

    @Test
    @DisplayName("Should addBookToCartItem - Increase Book quantity in CartItem")
    void addBookToCartItem() {
        Book book = new Book();
        cartItem1.setBook(book);

        given(bookServiceMock.findBookById(Mockito.anyLong())).willReturn(book);
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItem1);

        //CartItem result = cartItemService.addBookToCartItem(1L);

        //assertThat(result, equalTo(cartItem1));
    }

    @Test
    @DisplayName("Should removeOneCartItem - Delete CartItem, because quantity decrease from 1 to 0")
    void removeOneCartItem() {
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItem1);

        cartItemService.removeOneCartItem(1L, 1L);

        verify(cartSummaryServiceMock, times(1)).deleteCartItemById(Mockito.anyLong(), Mockito.anyLong());
    }

    @Test
    @DisplayName("Should removeOneCartItem - Save CartItem with new quantity (CartItem quantity > 1)")
    void removeOneCartItemSaveNewQuantity() {
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItem2);

        cartItemService.removeOneCartItem(1L, 1L);

        verify(cartItemRepoMock, times(1)).save(cartItem2);
    }

    @Test
    @DisplayName("Should addOneCartItem - Verify CartItemRepository.save invocation Success")
    void addOneCartItem() {
        given(cartItemRepoMock.findByCartItemId(Mockito.anyLong())).willReturn(cartItem1);

        cartItemService.addOneCartItem(1L);

        verify(cartItemRepoMock).save(cartItem1);
        verify(cartItemRepoMock, times(1)).save(cartItem1);
    }

    @Test
    void getTotalAmount() {
    }
}