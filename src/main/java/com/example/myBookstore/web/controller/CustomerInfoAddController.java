package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CustomerInfoService;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customerInfoAdd")
public class CustomerInfoAddController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @ModelAttribute("customerInfo")
    public CustomerInfoAddDto customerInfoAddDto() {
        return new CustomerInfoAddDto();
    }

    @GetMapping
    public String showCustromerInfoPage() {

        return "customerInfoAdd";
    }

    @PostMapping
    public String addCustomerInfoToCartSummary(@Valid @ModelAttribute("customerInfo") CustomerInfoAddDto customerInfoAddDto,
                                               BindingResult bindingResult) {

        int customerInfoList = customerInfoService.findAllCustomerInfos().size();
        if (customerInfoList > 0) {
            return "cartSummary";
        }

        if (bindingResult.hasErrors()) {
            return "customerInfoAdd";
        }

        customerInfoService.saveCustomerInfoToCartSummary(customerInfoAddDto);
        return "redirect:/customerInfoAdd?success";
    }
}
