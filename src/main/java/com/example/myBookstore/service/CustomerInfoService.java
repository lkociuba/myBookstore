package com.example.myBookstore.service;

import com.example.myBookstore.entity.CartSummary;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;

public interface CustomerInfoService {
    CustomerInfo saveCustomerInfo(CustomerInfoAddDto savingDto, CartSummary cartSummary);
}
