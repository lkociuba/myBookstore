package com.example.myBookstore.service;

import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private Cart cart;

    @Override
    public void deleteCartItem(Long cartItemId) {
        cart.deleteCartItem(cartItemId);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    @Override
    public void addCartItem(Long bookId) {
        cart.addCartItem(bookId);
    }

    @Override
    public void increaseCartItemQuantity(Long cartItemId) {
        cart.increaseCartItemQuantity(cartItemId);
    }

    @Override
    public void decreaseCartItemQuantity(Long cartItemId) {
        cart.decreaseCartItemQuantity(cartItemId);
    }

    @Override
    public double calculatedPrice() {
        return cart.calculatedPrice();
    }

    @Override
    public void deleteCartItemsAfterSaveOrder() {
        cart.deleteCartItemsAfterSaveOrder();
    }
}