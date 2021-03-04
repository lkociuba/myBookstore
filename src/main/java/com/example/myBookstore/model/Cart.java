package com.example.myBookstore.model;

import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.User;
import com.example.myBookstore.service.CataloqueServiceImpl;
import com.example.myBookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CataloqueServiceImpl cataloqueService;

    @Autowired
    private UserServiceImpl userService;

    private Book checkBookExist(Long bookId) {
        Book book = null;
        if (bookId != null && bookId > 0) {
            book = cataloqueService.findBookById(bookId);
        }
        return book;
    }

    public List<CartItem> getCartItems() {
        Long loggedUserId = userService.getLoogedUserId();
        List<CartItem> cartItemList = cartItemRepository.findAll();

        List<CartItem> loggedUserItemList = new ArrayList<>();
        for (CartItem item : cartItemList) {
            if (item.getUser().getId().equals(loggedUserId)) {
                loggedUserItemList.add(item);
            }
        }
        return loggedUserItemList;
    }

    private CartItem createNewCartItem(User user, Book book) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setBook(book);
        cartItem.setUser(user);
        return cartItem;
    }

    public void addCartItem(Long bookId) {
        User user = userService.findUser();
        Book book = this.checkBookExist(bookId);

        CartItem cartItem = null;
        if (book != null) {
            if (book.getCartItem() != null) {
                cartItem = book.getCartItem();
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                cartItem = this.createNewCartItem(user, book);
            }
        }
        cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        if (cartItemId != null) {
            CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId);
            cartItemRepository.delete(cartItem);
        }
    }

    public void increaseCartItemQuantity(Long cartItemId) {
        if (cartItemId != null) {
            CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        }
    }

    public void decreaseCartItemQuantity(Long cartItemId) {
        if (cartItemId != null) {
            CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId);
            if (cartItem.getQuantity() <= 1) {
                this.deleteCartItem(cartItemId);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            }
        }
    }

    public double calculatedPrice(){
        double calculatedPrice = 0;
        List<CartItem> cartItemList = this.getCartItems();

        for (CartItem item : cartItemList) {
            calculatedPrice += item.getQuantity() * item.getBook().getPrice();
        }
        return calculatedPrice;
    }
}
