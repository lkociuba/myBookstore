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
    public String showCart(ModelMap model) {
        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("calculatedPrice", cartService.calculatedPrice());
        return "cart";
    }

    @GetMapping("/buyBook/{bookId}")
    public String addBookToShoppingCart(@PathVariable(value = "bookId") Long bookId, ModelMap model) {
        cartService.addCartItem(bookId);
        return "redirect:/cart";
    }

    @GetMapping("/page/buyBook/{bookId}")
    public String addBookToShoppingCartFromPaginatedPage(@PathVariable(value = "bookId") Long bookId, ModelMap model) {
        cartService.addCartItem(bookId);
        return "redirect:/cart";
    }

    @GetMapping("deleteCartItem/{cartItemId}")
    public String deleteCartItemFromShoppingCart(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.deleteCartItem(cartItemId);
        return "redirect:/cart";
    }

    @GetMapping("/decreaseCartItemQuantity/{cartItemId}")
    public String decreaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId,  ModelMap model) {
        cartService.decreaseCartItemQuantity(cartItemId);
        return "redirect:/cart";
    }

    @GetMapping("/increaseCartItemQuantity/{cartItemId}")
    public String increaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.increaseCartItemQuantity(cartItemId);
        return "redirect:/cart";
    }





    @GetMapping("/cartSummary")
    public String showCartSummary(HttpServletRequest request, ModelMap model) {
        CartInfo cartInfo = Utils.getCartSession(request);

        if (cartInfo.isEmpty() || cartInfo == null) {
            return "redirect:/cart";
        }

        if (cartInfo.getCustomerInfo() == null) {
            return "redirect:/customerInfoAdd";
        }

        model.addAttribute("cartSummary", cartInfo);
        return "cartSummary";
    }
}