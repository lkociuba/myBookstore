package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CartServiceImpl;
import com.example.myBookstore.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private CustomerServiceImpl customerService;

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
    public String decreaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.decreaseCartItemQuantity(cartItemId);
        return "redirect:/cart";
    }

    @GetMapping("/increaseCartItemQuantity/{cartItemId}")
    public String increaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.increaseCartItemQuantity(cartItemId);
        return "redirect:/cart";
    }

    @GetMapping("/cartSummary")
    public String showCartSummary(ModelMap model) {
        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("calculatedPrice", cartService.calculatedPrice());
        model.addAttribute("customerInfo", customerService.findCustomerInfo());
        return "cartSummary";

    }

}