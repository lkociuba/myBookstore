package com.example.myBookstore.service;


import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findAllCartItems();
    CartItem findByCartItemId(Long cartItemId);

    Long convertIdFromStringToLong (String id);
    CartItem createCartItemWithBook (Book book);

    CartItem addBookToCartItem (String bookId);
    void deleteCartItem(String cartItemId);
    void removeOneCartItem (String cartItemId, String cartSummaryId);
    void addOneCartItem (String cartItemId);
    double getTotalAmount ();





}
