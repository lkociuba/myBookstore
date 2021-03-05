package com.example.myBookstore.service;

import com.example.myBookstore.dao.OrderItemRepository;
import com.example.myBookstore.dao.OrderRepository;
import com.example.myBookstore.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private CustomerServiceImpl customerService;

    private int getOrderMaxNumber() {
        int maxOrderNumber = 0;
        List<Order> orderList = this.getOrders();
        if (orderList.isEmpty()) {
            return maxOrderNumber;
        }

        for (Order order : orderList) {
            if (order.getOrderNumber() > maxOrderNumber) {
                maxOrderNumber = order.getOrderNumber();
            }
        }
        return maxOrderNumber;
    }

    @Override
    public void saveOrder() {
        User user = userService.findUser();
        int orderNumber = this.getOrderMaxNumber() + 1;
        double calculatedPrice = cartService.calculatedPrice();
        CustomerInfo customerInfo = customerService.findCustomerInfo();

        Order order = new Order(user, orderNumber, calculatedPrice, customerInfo);
        orderRepository.save(order);


        List<CartItem> cartItemList = cartService.getCartItems();
        for (CartItem item : cartItemList) {
            Book book = item.getBook();

            OrderItem orderItem = new OrderItem(order, book);
            orderItemRepository.save(orderItem);
        }

        cartService.deleteCartItemsAfterSaveOrder();
        customerService.deleteCustomerIfoAfterSaveOrder();
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findLoggedUserOrders() {
        Long loggedUserId = userService.getLoogedUserId();
        List<Order> orderList = this.getOrders();

        List<Order> loggedUserOrderList = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getId().equals(loggedUserId)) {
                loggedUserOrderList.add(order);
            }
        }
        return orderList;
    }
}
