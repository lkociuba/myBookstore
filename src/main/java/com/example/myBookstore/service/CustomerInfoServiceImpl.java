package com.example.myBookstore.service;

import com.example.myBookstore.dao.CustomerInfoRepository;
import com.example.myBookstore.entity.CartSummary;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private CartSummaryService cartSummaryService;

    @Override
    public CustomerInfo saveCustomerInfoToCartSummary(CustomerInfoAddDto savingDto) {
        CustomerInfo customerInfo = new CustomerInfo(savingDto.getCustomerName(), savingDto.getCustomerAddress(),
                savingDto.getCustomerEmail(), savingDto.getCustomerPhone());

        CartSummary cartSummary = null;
        List<CartSummary> cartSummaryList = cartSummaryService.findAllCartSummary();
        for (CartSummary summary : cartSummaryList){
            cartSummary = summary;
        }

        customerInfo.setCartSummary(cartSummary);
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public List<CustomerInfo> findAllCustomerInfos() {
        return customerInfoRepository.findAll();
    }
}


/*
 @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Override
    public CustomerInfo saveCustomerInfoToCartSummary(CustomerInfoAddDto savingDto) {
        CustomerInfo customerInfo = new CustomerInfo(savingDto.getCustomerName(), savingDto.getCustomerAddress(),
                savingDto.getCustomerEmail(), savingDto.getCustomerPhone());


        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public List<CustomerInfo> findAllCustomerInfos() {
        return customerInfoRepository.findAll();
    }
 */