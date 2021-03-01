package com.example.myBookstore.model;

import com.example.myBookstore.entity.Book;

public class BookInfo {

    private Long bookId;
    private String name;
    private String description;
    private double price;

    public BookInfo(){}

    public BookInfo(Book book){
        this.bookId = book.getBookId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.price = book.getPrice();
    }

    //For JPA querry
    public BookInfo(Long bookId, String name, String description, double price) {
        this.bookId = bookId;
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
}
