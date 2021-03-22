package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CustomerServiceImpl;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customerInfo")
public class CustomerInfoAddController {

    @Autowired
    private CustomerServiceImpl customerService;

    @ModelAttribute("customerInfo")
    public CustomerInfoAddDto customerInfoAddDto() {
        return new CustomerInfoAddDto();
    }

    @GetMapping
    public String showCustromerInfoPage(ModelMap model) {
        return "customerInfoAdd";
    }

    @PostMapping
    public String addCustomerInfoToCartSummary(@Valid @ModelAttribute("customerInfo") CustomerInfoAddDto customerInfoAddDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customerInfoAdd";
        }

        if (customerService.findCustomerInfo() != null) {
            return "redirect:/cartSummary";
        }

        customerService.saveCustomerInfoToCartSummary(customerInfoAddDto);
        return "redirect:/customerInfo?success";
    }

}
