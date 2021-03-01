package com.example.myBookstore.web.controller;

import com.example.myBookstore.model.CartInfo;
import com.example.myBookstore.model.CustomerInfo;
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
        return "customerInfoAdd";
    }

    @PostMapping
    public String addCustomerInfoToCartSummary(@Valid @ModelAttribute("customerInfo") CustomerInfoAddDto customerInfoAddDto,
                                               HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customerInfoAdd";
        }
        CartInfo cartInfo = Utils.getCartSession(request);

        CustomerInfo customerInfo = new CustomerInfo(customerInfoAddDto);

        cartInfo.setCustomerInfo(customerInfo);

        return "redirect:/customerInfoAdd?success";
    }


}
/*
 @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
   public String shoppingCartCustomerSave(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {

      if (result.hasErrors()) {
         customerForm.setValid(false);
         // Forward to reenter customer info.
         return "shoppingCartCustomer";
      }

      customerForm.setValid(true);
      CartInfo cartInfo = Utils.getCartInSession(request);
      CustomerInfo customerInfo = new CustomerInfo(customerForm);
      cartInfo.setCustomerInfo(customerInfo);

      return "redirect:/shoppingCartConfirmation";
   }

 */

/*
package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CustomerService;
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
    private CustomerService customerService;

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

        int customerInfoList = customerService.findAllCustomerInfos().size();
        if (customerInfoList > 0) {
            return "cartSummary";
        }

        if (bindingResult.hasErrors()) {
            return "customerInfoAdd";
        }

        customerService.saveCustomerInfoToCartSummary(customerInfoAddDto);
        return "redirect:/customerInfoAdd?success";
    }
}

 */