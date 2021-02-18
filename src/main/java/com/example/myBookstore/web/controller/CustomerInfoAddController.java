package com.example.myBookstore.web.controller;

import com.example.myBookstore.entity.CartSummary;
import com.example.myBookstore.service.CartSummaryService;
import com.example.myBookstore.service.CustomerInfoService;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerInfoAdd")
public class CustomerInfoAddController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private CartSummaryService cartSummaryService;

    @ModelAttribute("customerInfo")
    public CustomerInfoAddDto customerInfoAddDto() {
        return new CustomerInfoAddDto();
    }

    @GetMapping
    public String showCustromerInfoPage() {
        return "customerInfo";
    }

    @PostMapping
    public String addCustomerInfoToCartSummary(@ModelAttribute("customer") CustomerInfoAddDto customerInfoAddDto,
                                               @RequestParam("cartSummaryId") String cartSumaryId,
                                               ModelMap model) {
        CartSummary cartSummary = cartSummaryService.findCartSummaryById(Long.valueOf(cartSumaryId));
        return "redirect:/customerInfo?success";
    }
}