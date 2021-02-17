package com.example.myBookstore.entity;

import com.example.myBookstore.model.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "cart_summary")
public class CartSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_summary_id")
    private Long cartSummaryId;

    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
                name = "cart_summary_item",
            joinColumns = @JoinColumn(
                    name = "cart_summary_id", referencedColumnName = "summary_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "cart_item_id", referencedColumnName = "item_id")
    )
    private List<CartItem> cartItems = new ArrayList<>();

     */

    @OneToMany(mappedBy = "cartSummary", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();


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

    public void deleteCartItem(CartItem cartItem){
        this.cartItems.remove(cartItem);
    }


    public double getTotalAmount() {
        double totalAmount = 0;
        for (CartItem item : this.cartItems) {
            totalAmount += item.getAmount();
        }
        return totalAmount;
    }
}



