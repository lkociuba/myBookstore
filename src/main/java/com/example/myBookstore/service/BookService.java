package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.model.BookInfo;
import com.example.myBookstore.web.dto.BookAddDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Page<Book> getBooksPaginategAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection);

    Book addBook(BookAddDto savingDto);

    Book findBookById(Long bookId);

    BookInfo getBookInfo(Long bookId);
}

/*
package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.web.dto.BookAddDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Page<Book> getBooksPaginategAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection);

    Book addBook(BookAddDto savingDto);

    Book findBookById(Long bookId);
}

 */