package com.example.myBookstore.service;


import com.example.myBookstore.dao.CartItemRepository;
import com.example.myBookstore.dao.CartSummaryRepository;
import com.example.myBookstore.entity.Book;
import com.example.myBookstore.entity.CartItem;
import com.example.myBookstore.entity.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartSummaryService cartSummaryService;


    @Override
    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findByCartItemId(Long cartItemId) {
        return cartItemRepository.findByCartItemId(cartItemId);
    }

    @Override
    public Long convertIdFromStringToLong(String id) {
        Long convertedId = Long.valueOf(id);
        return convertedId;
    }

    @Override
    public CartItem createCartItemWithBook(Book book) {
        CartItem createdCartItem = new CartItem();
        createdCartItem.setQuantity(1);
        createdCartItem.setBook(book);

        return createdCartItem;
    }

    @Override
    public CartItem addBookToCartItem(String bookId) {
        final Long longBookId = this.convertIdFromStringToLong(bookId);
        Long cartItemIdRetrieveFromBook = null;
        CartItem cartItem = null;
        List<CartSummary> cartSummaries = cartSummaryService.findAllCartSummary();
        CartSummary cartSummary = null;

        Book book = null;
        if (bookId != null && bookId.length() > 0) {
            book = bookService.findBookById(longBookId);
        }

        if (book != null && book.getCartItem() != null) {
            cartItemIdRetrieveFromBook = book.getCartItem().getCartItemId();
            cartItem = cartItemRepository.findByCartItemId(cartItemIdRetrieveFromBook);
            cartItem.setQuantity(cartItem.getQuantity() + 1);

        } else if (book != null && cartSummaries.size() > 0) {
            cartItem = this.createCartItemWithBook(book);
            for (CartSummary summary : cartSummaries) {
                cartItem.setCartSummary(summary);
            }

        } else {
            cartItem = this.createCartItemWithBook(book);
            cartSummary = new CartSummary();
            cartItem.setCartSummary(cartSummary);

        }

        return cartItemRepository.save(cartItem);

    }


    @Override
    public void deleteCartItem(String cartItemId) {
        final Long longCartItemId = this.convertIdFromStringToLong(cartItemId);
        CartItem cartItem = null;
        CartSummary cartSummary = null;

        if (longCartItemId != null) {
            cartItem = cartItemRepository.findByCartItemId(longCartItemId);
            //cartSummary = cartItem.getCartSummary();
            cartSummary = cartSummaryService.findCartSummaryById(cartItem.getCartSummary().getCartSummaryId());
            cartSummary.deleteCartItem(cartItem);
        }
    }


    @Override
    public void removeOneCartItem(String cartItemId, String cartSummaryId) {
        final Long longCartItemId = this.convertIdFromStringToLong(cartItemId);
        CartItem cartItem = null;
        CartSummary cartSummary = null;

        if (longCartItemId != null) {
            cartItem = cartItemRepository.findByCartItemId(longCartItemId);
            if (cartItem.getQuantity() <= 1) {

                cartSummaryService.deleteCartItemById(cartSummaryId, cartItemId);

            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            }
        }
    }

    @Override
    public void addOneCartItem(String cartItemId) {
        final Long longCartItemId = this.convertIdFromStringToLong(cartItemId);
        CartItem cartItem = null;
        if (longCartItemId != null) {
            cartItem = cartItemRepository.findByCartItemId(longCartItemId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public double getTotalAmount() {
        double totalAmount = 0;
        List<CartItem> cartItems = cartItemRepository.findAll();
        for (CartItem item : cartItems) {
            totalAmount += item.getAmount();
        }
        return totalAmount;
    }
}


        /*
        if (cartItem != null){
            for (CartItem item : cartItem){
                CartItem cartItemUpdateQuantity = cartItemRepository.findByCartItemId(item.getCartItemId());
                cartItemUpdateQuantity.setQuantity(item.getQuantity());
                cartItemRepository.save(cartItemUpdateQuantity);
            }
        }
    }

         */

        /*
          if (cartItem != null){
            List<CartItem> cartItems = cartItemRepository.findAll();
            for (CartItem item : cartItems){
                CartItem cartItemUpdateQuantity = cartItemRepository.findByCartItemId(cartItem.getCartItemId());
                if (item != null){
                    item.setQuantity(cartItem.getQuantity());
                }

            }
          // cartItemUpdateQunantity = cartItemRepository.findByCartItemId(cartItem.getCartItemId());
          // cartItemUpdateQunantity.setQuantity(cartItem.getQuantity());
            /*
            if (item.getCartItemId().equals(cartItem.getCartItemId()) ){
                    CartItem cartItem1 = cartItemRepository.findByCartItemId(item.getCartItemId());
                    cartItem1.setQuantity(cartItem.getQuantity());
                }
             */
        /* to działąło
           CartItem cartItemUpdateQuantity = cartItemRepository.findByCartItemId(cartItem.getCartItemId());
        cartItemUpdateQuantity.setQuantity(cartItem.getQuantity());

        cartItemRepository.save(cartItemUpdateQuantity);
         */




/*
@Override
    public CartItem addBookToCartItem(String bookId) {

        CartItem cartItem = null;
        //final Long longBookId = Long.valueOf(bookId);
        final Long longBookId = this.convertIdFromStringToLong(bookId);
        Long cartItemIdRetrieveFromBook = null;

        Book book = null;
        if (bookId != null && bookId.length() > 0) {
            book = bookService.findBookById(longBookId);
        }

        if (book != null && book.getCartItem() != null){
            cartItemIdRetrieveFromBook = book.getCartItem().getCartItemId();
            cartItem = cartItemRepository.findByCartItemId(cartItemIdRetrieveFromBook);
            cartItem.setQuantity(cartItem.getQuantity()+1);

        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setBook(book);
        }

        return cartItemRepository.save(cartItem);

    }
 */