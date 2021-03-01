package com.example.myBookstore.web.controller;


import com.example.myBookstore.model.CartInfo;
import com.example.myBookstore.service.CartServiceImpl;
import com.example.myBookstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/cart")
    public String showCart(HttpServletRequest request, ModelMap model) {
        CartInfo cartInfo = Utils.getCartSession(request);
        model.addAttribute("shoppingCart", cartInfo);
        return "cart";
    }

    @GetMapping("/buyBook/{bookId}")
    public String addBookToShoppingCart(@PathVariable(value = "bookId") Long bookId,
                                        HttpServletRequest request, ModelMap model) {
        cartService.addCartItem(bookId, request);
        return "redirect:/cart";
    }

    @GetMapping("/page/buyBook/{bookId}")
    public String addBookToShoppingCartFromPaginatedPage(@PathVariable(value = "bookId") Long bookId,
                                                         HttpServletRequest request, ModelMap model) {
        cartService.addCartItem(bookId, request);
        return "redirect:/cart";
    }

    @GetMapping("deleteCartItem/{bookId}")
    public String deleteCartItemFromShoppingCart(@PathVariable(value = "bookId") Long bookId,
                                                 HttpServletRequest request, ModelMap model) {
        cartService.deleteCartItem(bookId, request);
        return "redirect:/cart";
    }

    @GetMapping("/removeOneCartItem/{bookId}")
    public String removeOneCartItemFromShoppingCart(@PathVariable(value = "bookId") Long bookId,
                                                    HttpServletRequest request, ModelMap model) {
        cartService.decreaseCartItemQuantity(bookId, request);
        return "redirect:/cart";
    }

    @GetMapping("/addOneCartItem/{bookId}")
    public String addOneCartItemToShoppingCart(@PathVariable(value = "bookId") Long bookId,
                                               HttpServletRequest request, ModelMap model) {
        cartService.increaseCartItemQuantity(bookId, request);
        return "redirect:/cart";
    }

    @GetMapping("/cartSummary")
    public String showCartSummary(HttpServletRequest request, ModelMap model) {
        CartInfo cartInfo = Utils.getCartSession(request);
        model.addAttribute("cartSummary", cartInfo);
        return "cartSummary";
    }
}