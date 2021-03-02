package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    Book checkBookExist(Long bookId);

    void addCartItem(Long bookId, HttpServletRequest request);

    void deleteCartItem(Long bookId, HttpServletRequest request);

    void decreaseCartItemQuantity(Long bookId, HttpServletRequest request);

    void increaseCartItemQuantity(Long bookId, HttpServletRequest request);

    void saveCustomerInfo(CustomerInfoAddDto customerInfoAddDto, HttpServletRequest request);
}
