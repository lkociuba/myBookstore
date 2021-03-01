package com.example.myBookstore.utils;

import com.example.myBookstore.model.CartInfo;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static CartInfo getCartSession(HttpServletRequest request) {

        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("shopingCart");

        if (cartInfo == null) {
            cartInfo = new CartInfo();
            request.getSession().setAttribute("shopingCart", cartInfo);
        }
        return cartInfo;
    }
}
