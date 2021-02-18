package com.example.myBookstore.service;


import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findAllCartItems();

    CartItem findByCartItemId(Long cartItemId);

    CartItem createCartItemWithBook(Book book);

    CartItem addBookToCartItem(Long bookId);

    void removeOneCartItem(Long cartItemId, Long cartSummaryId);

    void addOneCartItem(Long cartItemId);

    double getTotalAmount();

}

