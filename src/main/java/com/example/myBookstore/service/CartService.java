package com.example.myBookstore.service;

import com.example.myBookstore.entity.CartItem;

import java.util.List;

public interface CartService {

    void deleteCartItem(Long cartItemId);

    List<CartItem> getCartItems();

    void addCartItem(Long bookId);

    void increaseCartItemQuantity(Long cartItemId);

    void decreaseCartItemQuantity(Long cartItemId);

    double calculatedPrice();
}