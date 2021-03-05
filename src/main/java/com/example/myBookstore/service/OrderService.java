package com.example.myBookstore.service;

import com.example.myBookstore.entity.Order;

import java.util.List;

public interface OrderService {
    int getOrderMaxNumber();

    List<Order> getOrders();

    List<Order> findLoggedUserOrders();

    void saveOrder();
}
