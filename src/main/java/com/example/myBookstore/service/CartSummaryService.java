package com.example.myBookstore.service;

import com.example.myBookstore.entity.CartSummary;

import java.util.List;

public interface CartSummaryService {
    List<CartSummary> findAllCartSummary();
    CartSummary findCartSummaryById(Long cartSummaryId);
    void deleteCartItemById (String cartSumaryId, String cartItemId);

}
