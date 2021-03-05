package com.example.myBookstore.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public CartItem() {
    }

    public CartItem(Book book, int quantity) {
        super();
        this.book = book;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getItemFinalPrice() {
        double amount = 0;
        amount += this.quantity * this.getBook().getPrice();
        return amount;
    }
}
