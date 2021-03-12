package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CartServiceImpl;
import com.example.myBookstore.service.CustomerServiceImpl;
import com.example.myBookstore.service.UserServiceImpl;
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

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/shoppingCart")
    public String showCart(ModelMap model) {
        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("calculatedPrice", cartService.calculatedPrice());
        return "cart";
    }

    @GetMapping("/buyBook/{bookId}")
    public String addBookToShoppingCart(@PathVariable(value = "bookId") Long bookId, ModelMap model) {
        if (userService.findUser() == null) {
            return "redirect:/login";
        }
        cartService.addCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/page/buyBook/{bookId}")
    public String addBookToShoppingCartFromPaginatedPage(@PathVariable(value = "bookId") Long bookId, ModelMap model) {
        cartService.addCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("deleteCartItem/{cartItemId}")
    public String deleteCartItemFromShoppingCart(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.deleteCartItem(cartItemId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/decreaseCartItemQuantity/{cartItemId}")
    public String decreaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.decreaseCartItemQuantity(cartItemId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/increaseCartItemQuantity/{cartItemId}")
    public String increaseCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap model) {
        cartService.increaseCartItemQuantity(cartItemId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCartSummary")
    public String showCartSummary(ModelMap model) {
        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("calculatedPrice", cartService.calculatedPrice());
        model.addAttribute("customerInfo", customerService.findCustomerInfo());
        return "cartSummary";

    }

}