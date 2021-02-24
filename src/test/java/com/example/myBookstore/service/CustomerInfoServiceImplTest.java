package com.example.myBookstore.service;

import com.example.myBookstore.dao.CustomerInfoRepository;
import com.example.myBookstore.entity.CartSummary;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerInfoServiceImplTest {

    @Mock
    private CustomerInfoRepository customerInfoRepoMock;

    @Mock
    private CartSummaryService cartSummaryServiceMock;

    @InjectMocks
    private CustomerInfoServiceImpl customerInfoService;


    private CustomerInfo customerInfo1;
    private CustomerInfo customerInfo2;
    private List<CustomerInfo> customerInfoList;

    @BeforeEach
    void init() {
        customerInfo1 = new CustomerInfo();
        customerInfo1.setCustomerName("Jon Snow");
        customerInfo1.setCustomerEmail("jon.snow@email.com");
        customerInfo1.setCustomerAddress("The North");

        customerInfo2 = new CustomerInfo();
        customerInfo2.setCustomerName("Arya Stark");
        customerInfo2.setCustomerEmail("arya.stark@email.com");
        customerInfo2.setCustomerAddress("Winterfell");

        customerInfoList = new ArrayList<>();
        customerInfoList.add(customerInfo1);
        customerInfoList.add(customerInfo2);
    }

    @Test
    @DisplayName("Should saveCustomerInfoToCartSummary - Not null value")
    void saveCustomerInfoToCartSummary() {
        CartSummary cartSummary = new CartSummary();
        List<CartSummary> cartSummaryList = new ArrayList<>();
        cartSummaryList.add(cartSummary);
        given(cartSummaryServiceMock.findAllCartSummary()).willReturn(cartSummaryList);
        given(customerInfoRepoMock.save(Mockito.anyObject())).willReturn(new CustomerInfo());

        CustomerInfoAddDto customerInfoAddDto = new CustomerInfoAddDto();

        assertThat(customerInfoService.saveCustomerInfoToCartSummary(customerInfoAddDto), is(notNullValue()));
    }

    @Test
    @DisplayName("Should findAllCustomerInfos - Success")
    void findAllCustomerInfos() {
        given(customerInfoRepoMock.findAll()).willReturn(customerInfoList);

        List<CustomerInfo> result = customerInfoService.findAllCustomerInfos();

        assertThat(result.size(), is(2));
        assertThat(result, equalTo(customerInfoList));
    }

    @Test
    @DisplayName("Should findAllCustomerInfos - List is empty")
    void findAllCustomerInfosIsEmpty() {
        List<CustomerInfo> result = customerInfoService.findAllCustomerInfos();

        assertThat(result.size(), is(0));
    }
}