package com.example.myBookstore.entity;

import com.example.myBookstore.web.dto.CustomerInfoAddDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CustomerInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerInfoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;

    public CustomerInfo() {
    }

    public CustomerInfo(CustomerInfoAddDto customerInfoAddDto) {
        this.customerName = customerInfoAddDto.getCustomerName();
        this.customerAddress = customerInfoAddDto.getCustomerAddress();
        this.customerEmail = customerInfoAddDto.getCustomerEmail();
        this.customerPhone = customerInfoAddDto.getCustomerPhone();
    }

    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
