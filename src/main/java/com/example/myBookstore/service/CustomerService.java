package com.example.myBookstore.service;

import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;

import java.util.List;

public interface CustomerService {
    CustomerInfo saveCustomerInfoToCartSummary(CustomerInfoAddDto savingDto);

    CustomerInfo findCustomerInfo();
}
