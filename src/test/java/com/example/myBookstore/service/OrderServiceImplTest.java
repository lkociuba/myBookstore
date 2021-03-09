package com.example.myBookstore.service;

import com.example.myBookstore.dao.OrderItemRepository;
import com.example.myBookstore.dao.OrderRepository;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.Order;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.model.Cart;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepoMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @Mock
    private CartServiceImpl cartServiceMock;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private OrderItemRepository orderItemRepoMock;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User userInit1;
    private Order orderInit1;
    private List<Order> orderListInit;

    @BeforeEach
    void init() {
        userInit1 = new User();
        userInit1.setId(1L);


        orderInit1 = new Order();
        orderInit1.setOrderNumber(2);
        orderInit1.setUser(userInit1);

        orderListInit = new ArrayList<>();
        orderListInit.add(orderInit1);
    }

    @Test
    @DisplayName("Should getOrderMaxNumber - Result should be 2")
    void getOrderMaxNumber() {
        given(orderRepoMock.findAll()).willReturn(orderListInit);

        int result = orderService.getOrderMaxNumber();

        assertThat(result, is(2));
    }

    @Test
    @DisplayName("Should getOrderMaxNumber - Result should be 0 (empty list)")
    void getOrderMaxNumberEmptyList() {
        orderListInit.clear();
        given(orderRepoMock.findAll()).willReturn(orderListInit);

        int result = orderService.getOrderMaxNumber();

        assertThat(result, is(0));
    }

    @Test
    @DisplayName("Should getOrderMaxNumber - Result should be 0 (pass negative number)")
    void getOrderMaxNumberMinus() {
        orderInit1.setOrderNumber(-3);
        given(orderRepoMock.findAll()).willReturn(orderListInit);

        int result = orderService.getOrderMaxNumber();

        assertThat(result, is(0));
    }

    @Test
    void saveOrder() {
        given(orderService.getOrders()).willReturn(orderListInit);
        given(cartServiceMock.calculatedPrice()).willReturn(10.0);
        given(customerService.findCustomerInfo()).willReturn(new CustomerInfo());

        orderService.saveOrder();

        verify(orderRepoMock, times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Should getOrders - success")
    void getOrders() {
        given(orderRepoMock.findAll()).willReturn(orderListInit);

        List<Order> result = orderService.getOrders();

        assertThat(result, is(orderListInit));
    }

    @Test
    @DisplayName("Should findLoggedUserOrders - Success")
    void findLoggedUserOrders() {
        given(userServiceMock.getLoogedUserId()).willReturn(1L);
        given(orderService.getOrders()).willReturn(orderListInit);

        List<Order> result = orderService.findLoggedUserOrders();

        assertThat(result, is(orderListInit));
    }

    @Test
    @DisplayName("Should findLoggedUserOrders - Empty list (no Order related with User)")
    void findLoggedUserOrdersEmpty() {
        given(userServiceMock.getLoogedUserId()).willReturn(2L);
        given(orderService.getOrders()).willReturn(orderListInit);

        List<Order> result = orderService.findLoggedUserOrders();

        assertThat(result.size(), is(0));
    }
}