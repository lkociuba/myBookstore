package com.example.myBookstore.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long bookId;

    private String name;
    private String description;
    private double price;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "book")
    private CartItem cartItem;

    public CartItem getCartItem() {
        return cartItem;
    }

    @CreationTimestamp
    private LocalDateTime createdTime;

    public Book() {
    }


    public Book(String name, String description, double price) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

}
