package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/saveOrder")
    public String showCart(ModelMap model) {
        orderService.saveOrder();
        return "redirect:/finalizeOrder";
    }

    @GetMapping("/finalizeOrder")
    public String showSavedOrderNumber(ModelMap model) {
        model.addAttribute("orderNumber", orderService.getOrderMaxNumber());
        return "orderFinalize";
    }
}