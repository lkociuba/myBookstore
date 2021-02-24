package com.example.myBookstore.service;


import com.example.myBookstore.dao.CartItemRepository;
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
    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem findByCartItemId(Long cartItemId) {
        return cartItemRepository.findByCartItemId(cartItemId);
    }

    @Override
    public CartItem createCartItemWithBook(Book book) {
        CartItem createdCartItem = new CartItem();
        createdCartItem.setQuantity(1);
        createdCartItem.setBook(book);

        return createdCartItem;
    }

    @Override
    public CartItem addBookToCartItem(Long bookId) {
        Long cartItemIdRetrieveFromBook = null;
        CartItem cartItem = null;
        List<CartSummary> cartSummaries = cartSummaryService.findAllCartSummary();
        CartSummary cartSummary = null;

        Book book = null;
        if (bookId != null && bookId > 0) {
            book = bookService.findBookById(bookId);
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
    public void removeOneCartItem(Long cartItemId, Long cartSummaryId) {
        CartItem cartItem = null;

        if (cartItemId != null) {
            cartItem = cartItemRepository.findByCartItemId(cartItemId);
            if (cartItem.getQuantity() <= 1) {
                cartSummaryService.deleteCartItemById(cartSummaryId, cartItemId);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            }
        }
    }

    @Override
    public void addOneCartItem(Long cartItemId) {
        CartItem cartItem = null;

        if (cartItemId != null) {
            cartItem = cartItemRepository.findByCartItemId(cartItemId);
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