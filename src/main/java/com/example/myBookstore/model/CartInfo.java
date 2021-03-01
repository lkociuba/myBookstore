package com.example.myBookstore.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
    private CustomerInfo customerInfo;

    private final List<CartItemInfo> cartItemInfoList = new ArrayList<>();


    public CartInfo() {
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CartItemInfo> getCartItemInfoList() {
        return cartItemInfoList;
    }

    private CartItemInfo findCartItemInfo(Long bookInfoId) {
        for (CartItemInfo item : this.cartItemInfoList) {
            if (item.getBookInfo().getBookId().equals(bookInfoId)) {
                return item;
            }
        }
        return null;
    }

    public void addCartItem(BookInfo bookInfo) {
        CartItemInfo item = this.findCartItemInfo(bookInfo.getBookId());

        if (item == null) {
            item = new CartItemInfo();
            item.setQuantity(1);
            item.setBookInfo(bookInfo);
            this.cartItemInfoList.add(item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void deleteCartItem(BookInfo bookInfo) {
        CartItemInfo item = this.findCartItemInfo(bookInfo.getBookId());

        if (item != null) {
            this.cartItemInfoList.remove(item);
        }
    }

    public void decreaseCartItemQuantity(BookInfo bookInfo) {
        CartItemInfo item = this.findCartItemInfo(bookInfo.getBookId());

        if (item != null)
            if (item.getQuantity() <= 1) {
                this.cartItemInfoList.remove(item);
            } else {
                item.setQuantity(item.getQuantity() - 1);
            }
    }

    public void increaseCartItemQuantity(BookInfo bookInfo) {
        CartItemInfo item = this.findCartItemInfo(bookInfo.getBookId());

        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public double calculatedPrice() {
        double totalAmount = 0;
        List<CartItemInfo> cartItemInfoList = this.cartItemInfoList;

        for (CartItemInfo item : cartItemInfoList) {
            totalAmount += item.getQuantity() * item.getBookInfo().getPrice();
        }
        return totalAmount;
    }
}
