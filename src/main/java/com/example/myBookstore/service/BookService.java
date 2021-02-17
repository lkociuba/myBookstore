package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.web.dto.BookAddDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
   // Page<Book> findAllBooksPaginated (int pageNumber, int pageSize);  to było do pagination tylko
    Page<Book> findAllBooksPaginategAndSorted (int pageNumber, int pageSize, String sortField, String sortDirection);
    Book saveBook(BookAddDto savingDto);
    Book findBookById(Long bookId);

}
