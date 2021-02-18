package com.example.myBookstore.service;

import com.example.myBookstore.dao.CustomerInfoRepository;
import com.example.myBookstore.entity.CartSummary;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Override
    public CustomerInfo saveCustomerInfo(CustomerInfoAddDto savingDto, CartSummary cartSummary) {
        CustomerInfo customerInfo = new CustomerInfo(savingDto.getCustomerName(), savingDto.getCustomerAddress(),
                savingDto.getCustomerEmail(), savingDto.getCustomerPhone());

        customerInfo.setCartSummary(cartSummary);
        return customerInfoRepository.save(customerInfo);
    }
}
