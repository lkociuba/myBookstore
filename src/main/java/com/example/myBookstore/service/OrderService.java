package com.example.myBookstore.service;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    void saveOrder(HttpServletRequest request);
}
