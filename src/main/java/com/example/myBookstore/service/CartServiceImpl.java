package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.model.BookInfo;
import com.example.myBookstore.model.CartInfo;
import com.example.myBookstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private BookServiceImpl bookService;

    @Override
    public Book checkBookExist(Long bookId) {
        Book book = null;
        if (bookId != null && bookId > 1) {
            book = bookService.findBookById(bookId);
        }
        return book;
    }

    @Override
    public void addCartItem(Long bookId, HttpServletRequest request) {
        Book book = this.checkBookExist(bookId);

        if (book != null) {
            CartInfo cartInfo = Utils.getCartSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.addCartItem(bookInfo);
        }
    }

    @Override
    public void deleteCartItem(Long bookId, HttpServletRequest request) {
        Book book = this.checkBookExist(bookId);

        if (book != null) {
            CartInfo cartInfo = Utils.getCartSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.deleteCartItem(bookInfo);
        }
    }

    @Override
    public void decreaseCartItemQuantity(Long bookId, HttpServletRequest request) {
        Book book = this.checkBookExist(bookId);

        if (book != null) {
            CartInfo cartInfo = Utils.getCartSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.decreaseCartItemQuantity(bookInfo);
        }
    }

    @Override
    public void increaseCartItemQuantity(Long bookId, HttpServletRequest request) {
        Book book = this.checkBookExist(bookId);

        if (book != null) {
            CartInfo cartInfo = Utils.getCartSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.increaseCartItemQuantity(bookInfo);
        }
    }
}