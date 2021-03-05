package com.example.myBookstore.service;

import com.example.myBookstore.dao.CustomerRepository;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public CustomerInfo saveCustomerInfoToCartSummary(CustomerInfoAddDto savingDto) {
        CustomerInfo customerInfo = new CustomerInfo(savingDto);
        User user = userService.findUser();
        customerInfo.setUser(user);

        return customerRepository.save(customerInfo);
    }

    @Override
    public CustomerInfo findCustomerInfo() {
        User user = userService.findUser();
        return customerRepository.findByUser(user);
    }

    @Override
    public void deleteCustomerIfoAfterSaveOrder() {
        CustomerInfo customerInfo = this.findCustomerInfo();
        customerRepository.delete(customerInfo);
    }
}