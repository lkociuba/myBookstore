package com.example.myBookstore.service;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.dao.CartSummaryRepository;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSummaryServiceImpl implements CartSummaryService{

    @Autowired
    private CartSummaryRepository cartSummaryRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

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
    public void deleteCartItemById(String cartSumaryId, String cartItemId) {
        final Long longCartSummaryId = Long.valueOf(cartSumaryId);
        final Long longCartItemId = Long.valueOf(cartItemId);

        CartItem cartItem = null;
        CartSummary cartSummary = null;

        if (longCartItemId != null && longCartSummaryId != null) {
            //cartItem = cartItemRepository.findByCartItemId(longCartItemId);
            cartItem = cartItemService.findByCartItemId(longCartItemId);

            cartSummary = cartSummaryRepository.findByCartSummaryId(longCartSummaryId);
            cartSummary.deleteCartItem(cartItem);
            cartItemRepository.delete(cartItem);
        }

        cartSummaryRepository.save(cartSummary);


    }


}
