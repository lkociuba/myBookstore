package com.example.myBookstore.service;

import com.example.myBookstore.dao.CustomerRepository;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepoMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerInfo customerInfoInit1;
    private CustomerInfoAddDto customerInfoAddDtoInit1;
    private User userInit1;

    @BeforeEach
    void init() {
        userInit1 = new User();

        customerInfoInit1 = new CustomerInfo();
        customerInfoInit1.setCustomerName("Jacob");
        customerInfoInit1.setCustomerAddress("Poland");
        customerInfoInit1.setCustomerEmail("customer@email");
        customerInfoInit1.setCustomerPhone("999999999");
        customerInfoInit1.setUser(userInit1);

        customerInfoAddDtoInit1 = new CustomerInfoAddDto();
        customerInfoAddDtoInit1.setCustomerName("Jacob");
        customerInfoAddDtoInit1.setCustomerAddress("Poland");
        customerInfoAddDtoInit1.setCustomerEmail("customer@email");
        customerInfoAddDtoInit1.setCustomerPhone("999999999");
    }

    @Test
    @DisplayName("Should saveCustomerInfoToCartSummary - Success")
    void saveCustomerInfoToCartSummary() {
        given(userServiceMock.findUser()).willReturn(userInit1);
        given(customerRepoMock.save(Mockito.any())).willReturn(customerInfoInit1);

        CustomerInfo result = customerService.saveCustomerInfoToCartSummary(customerInfoAddDtoInit1);

        assertThat(result, is(customerInfoInit1));
    }

    @Test
    @DisplayName("Should saveCustomerInfoToCartSummary - Passing Null value give NullPointerException")
    void saveCustomerInfoToCartSummaryNullValue() {
        assertThrows(NullPointerException.class, () -> {
            customerService.saveCustomerInfoToCartSummary(null);
        });
    }

    @Test
    @DisplayName("Should findCustomerInfo - Success")
    void findCustomerInfo() {
        given(userServiceMock.findUser()).willReturn(userInit1);
        given(customerRepoMock.findByUser(userInit1)).willReturn(customerInfoInit1);

        CustomerInfo result = customerService.findCustomerInfo();

        assertThat(result, is(customerInfoInit1));
    }

    @Test
    @DisplayName("Should deleteCustomerIfoAfterSaveOrder - Verify customerRepository.delete invocation Success")
    void deleteCustomerIfoAfterSaveOrder() {
        given(customerService.findCustomerInfo()).willReturn(customerInfoInit1);

        customerService.deleteCustomerIfoAfterSaveOrder();
        customerService.deleteCustomerIfoAfterSaveOrder();

        verify(customerRepoMock, times(2)).delete(customerInfoInit1);
    }

    @Test
    @DisplayName("Should deleteCustomerIfoAfterSaveOrder - Verify customerRepository.delete no invocation (CustomerInfo not found")
    void deleteCustomerIfoAfterSaveOrderNotDeleted() {
        customerService.deleteCustomerIfoAfterSaveOrder();
        customerService.deleteCustomerIfoAfterSaveOrder();

        verify(customerRepoMock, times(0)).delete(customerInfoInit1);
    }
}
