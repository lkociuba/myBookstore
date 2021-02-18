package com.example.myBookstore.entity;

import javax.persistence.*;


@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_summary_id")
    private CartSummary cartSummary;

    public CartItem() {
    }

    public CartItem(int quantity, Book book) {
        super();
        this.quantity = quantity;
        this.book = book;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartSummary getCartSummary() {
        return cartSummary;
    }

    public void setCartSummary(CartSummary cartSummary) {
        this.cartSummary = cartSummary;
    }

    public double getAmount() {
        double amount = 0;
        amount += this.quantity * this.book.getPrice();
        return amount;
    }

}

