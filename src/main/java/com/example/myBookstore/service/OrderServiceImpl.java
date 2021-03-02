package com.example.myBookstore.service;

import com.example.myBookstore.model.CartInfo;
import com.example.myBookstore.utils.Utils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public void saveOrder(HttpServletRequest request) {
        CartInfo cartInfo = Utils.getCartSession(request);


    }
}
