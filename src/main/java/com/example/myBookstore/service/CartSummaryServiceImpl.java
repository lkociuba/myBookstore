package com.example.myBookstore.service;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.dao.CartSummaryRepository;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSummaryServiceImpl implements CartSummaryService {

    @Autowired
    private CartSummaryRepository cartSummaryRepository;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public List<CartSummary> findAllCartSummary() {
        return cartSummaryRepository.findAll();
    }

    @Override
    public CartSummary findCartSummaryById(Long cartSummaryId) {
        return cartSummaryRepository.findByCartSummaryId(cartSummaryId);
    }

    @Override
    public void deleteCartItemById(Long cartSummaryId, Long cartItemId) {
        CartItem cartItem = null;
        CartSummary cartSummary = null;

        if (cartItemId != null && cartSummaryId != null) {
            cartItem = cartItemService.findByCartItemId(cartItemId);

            cartSummary = cartSummaryRepository.findByCartSummaryId(cartSummaryId);
            cartSummary.deleteCartItem(cartItem);

            cartItemService.deleteCartItem(cartItem);

            cartSummaryRepository.save(cartSummary);
        }
    }
}
