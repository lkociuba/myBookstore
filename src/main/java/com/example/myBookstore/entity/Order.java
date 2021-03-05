package com.example.myBookstore.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private int orderNumber;

    @NotNull
    private double calculatedPrice;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(length = 255, nullable = false)
    private String customerName;

    @Column(length = 255, nullable = false)
    private String customerAddress;

    @Column(length = 255, nullable = false)
    private String customerEmail;

    @Column(length = 255, nullable = false)
    private String customerPhone;

    public Order(){}

    public Order(User user, int orderNumber, double calculatedPrice, CustomerInfo customerInfo) {
        this.user = user;
        this.orderNumber = orderNumber;
        this.calculatedPrice = calculatedPrice;
        this.customerName = customerInfo.getCustomerName();
        this.customerAddress = customerInfo.getCustomerAddress();
        this.customerEmail = customerInfo.getCustomerEmail();
        this.customerPhone = customerInfo.getCustomerPhone();
    }

    public Long getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(double calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
