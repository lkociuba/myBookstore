package com.example.myBookstore.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cart_summary")
public class CartSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_summary_id")
    private Long cartSummaryId;

    @OneToMany(mappedBy = "cartSummary", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cartSummary")
    private CustomerInfo customerInfo;

    public CartSummary() {
    }

    public CartSummary(List<CartItem> cartItems) {
        super();
        this.cartItems = cartItems;
    }

    public Long getCartSummaryId() {
        return cartSummaryId;
    }

    public void setCartSummaryId(Long cartSummaryId) {
        this.cartSummaryId = cartSummaryId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (CartItem item : this.cartItems) {
            totalAmount += item.getAmount();
        }
        return totalAmount;
    }
}



