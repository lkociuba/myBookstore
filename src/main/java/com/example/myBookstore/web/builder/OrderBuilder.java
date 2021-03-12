package com.example.myBookstore.web.builder;

import com.example.myBookstore.entity.CustomerInfo;
import com.example.myBookstore.entity.Order;
import com.example.myBookstore.entity.User;

public class OrderBuilder {
    private User user;
    private int orderNumber;
    private double calculatedPrice;
    private CustomerInfo customerInfo;

    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public OrderBuilder setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public OrderBuilder setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
        return this;
    }

    public OrderBuilder setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
        return this;
    }

    public Order getOrder() {
        return new Order(user, orderNumber, calculatedPrice, customerInfo);
    }
}
