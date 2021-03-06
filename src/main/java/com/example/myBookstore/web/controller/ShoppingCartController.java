package com.example.myBookstore.web.controller;

import com.example.myBookstore.service.CartItemService;
import com.example.myBookstore.service.CartSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ShoppingCartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartSummaryService cartSummaryService;

    @GetMapping("/buyBook/{bookId}")
    public String addBookToShoppingCart(@PathVariable(value = "bookId") Long bookId,
                                        ModelMap model) {
        cartItemService.addBookToCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/page/buyBook/{bookId}")
    public String addBookToShoppingCartFromPaginatedPage(@PathVariable(value = "bookId") Long bookId,
                                                         ModelMap model) {
        cartItemService.addBookToCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/deleteCartItem/{cartSummaryId}")
    public String deleteCartItemFromShoppingCart(@PathVariable(value = "cartSummaryId") Long cartSummaryId,
                                                 @RequestParam("cartItemId") Long cartItemId,
                                                 ModelMap model) {
        cartSummaryService.deleteCartItemById(cartSummaryId, cartItemId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/removeOneCartItem/{cartItemId}")
    public String removeOneCartItemFromShoppingCart(@PathVariable(value = "cartItemId") Long cartItemId,
                                                    @RequestParam("cartSummaryId") Long cartSumaryId,
                                                    ModelMap model) {
        cartItemService.removeOneCartItem(cartItemId, cartSumaryId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/addOneCartItem/{cartItemId}")
    public String addOneCartItemToShoppingCart(@PathVariable(value = "cartItemId") Long cartItemId,
                                               ModelMap model) {
        cartItemService.addOneCartItem(cartItemId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String showShoppingCart(ModelMap model) {
        model.addAttribute("cartItem", cartItemService.findAllCartItems());
        model.addAttribute("cartSummary", cartSummaryService.findAllCartSummary());
        model.addAttribute("totalAmount", cartItemService.getTotalAmount());
        return "shoppingCart";
    }
}