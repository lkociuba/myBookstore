package com.example.myBookstore.web.controller;

import com.example.myBookstore.model.CartInfo;
import com.example.myBookstore.service.CartServiceImpl;
import com.example.myBookstore.utils.Utils;
import com.example.myBookstore.web.dto.CustomerInfoAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("customerInfoAdd")
public class CustomerInfoAddController {

    @Autowired
    private CartServiceImpl cartService;

    @ModelAttribute("customerInfo")
    public CustomerInfoAddDto customerInfoAddDto() {
        return new CustomerInfoAddDto();
    }

    @GetMapping
    public String showCustromerInfoPage(HttpServletRequest request, ModelMap model) {
        CartInfo cartInfo = Utils.getCartSession(request);
        if (cartInfo.isEmpty()){
            return "redirect:/cart";
        }
        return "customerInfoAdd";
    }

    @PostMapping
    public String addCustomerInfoToCartSummary(@Valid @ModelAttribute("customerInfo") CustomerInfoAddDto customerInfoAddDto,
                                               HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customerInfoAdd";
        }

        return "redirect:/customerInfoAdd?success";
    }
}
