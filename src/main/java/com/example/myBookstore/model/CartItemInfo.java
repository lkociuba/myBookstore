package com.example.myBookstore.model;

public class CartItemInfo {

    private BookInfo bookInfo;
    private int quantity;

    public CartItemInfo() {
        this.quantity = 0;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){
        double amount = 0;
        amount += this.quantity * this.bookInfo.getPrice();
        return amount;
    }
}
