package com.example.myBookstore.entity;

import javax.persistence.*;

@Entity
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerInfoId;

    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_sumary_id")
    private CartSummary cartSummary;

    public CustomerInfo() {
    }

    public CustomerInfo(String customerName, String customerAddress, String customerEmail, String customerPhone) {
        super();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public Long getCustomerInfoId() {
        return customerInfoId;
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

    public CartSummary getCartSummary() {
        return cartSummary;
    }

    public void setCartSummary(CartSummary cartSummary) {
        this.cartSummary = cartSummary;
    }
}
