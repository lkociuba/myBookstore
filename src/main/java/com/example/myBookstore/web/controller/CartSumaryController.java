package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CartItemService;
import com.example.myBookstore.service.CartSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CartSumaryController {

    @Autowired
    private CartSummaryService cartSummaryService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cartSummary")
    public String showCartSummary(ModelMap model) {
        model.addAttribute("cartSummary", cartSummaryService.findAllCartSummary());
        model.addAttribute("cartItem", cartItemService.findAllCartItems());
        return "cartSummary";
    }
}
