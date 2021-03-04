package com.example.myBookstore.service;

import com.example.myBookstore.entity.Book;
import com.example.myBookstore.dao.BookRepository;
import com.example.myBookstore.model.BookInfo;
import com.example.myBookstore.web.dto.BookAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CataloqueServiceImpl implements CataloqueService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
    
    @Override
    public Page<Book> getBooksPaginategAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book addBook(BookAddDto bookAddDto) {
        Book book = new Book(bookAddDto.getName(), bookAddDto.getDescription(), bookAddDto.getPrice());
        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findByBookId(bookId);
    }

    @Override
    public BookInfo getBookInfo(Long bookId) {
        Book book = this.findBookById(bookId);
        if (book == null){
            return null;
        }
        return new BookInfo(book.getBookId(), book.getName(), book.getDescription(), book.getPrice());
    }
}