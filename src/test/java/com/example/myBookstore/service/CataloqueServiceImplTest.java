package com.example.myBookstore.service;

import com.example.myBookstore.dao.BookRepository;
import com.example.myBookstore.entity.Book;
import com.example.myBookstore.web.dto.BookAddDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CataloqueServiceImplTest {

    @Mock
    private BookRepository bookRepoMock;

    @InjectMocks
    private CataloqueServiceImpl bookService;

    private Book bookInit1;
    private Book bookInit2;
    private List<Book> bookList;

    @BeforeEach
    void init() {
        bookInit1 = new Book();
        bookInit1.setName("Reed book");
        bookInit1.setDescription("Red book about reed things");
        bookInit1.setPrice(30);

        bookInit2 = new Book();
        bookInit2.setName("Geographical atlas");
        bookInit2.setDescription("A simple atlas for beginners");
        bookInit2.setPrice(10);

        bookList = new ArrayList<>();
        bookList.add(bookInit1);
        bookList.add(bookInit2);
    }

    @Test
    @DisplayName("Should getBooks - Success")
    void findAllBooks() {
        given(bookRepoMock.findAll()).willReturn(bookList);

        List<Book> result = bookService.getBooks();

        assertThat(result.size(), is(2));
        assertThat(result, equalTo(bookList));
    }

    @Test
    @DisplayName("Should getBooks - List is empty")
    void findAllBooksIsEmpty() {
        List<Book> result = bookService.getBooks();

        assertThat(result.size(), is(0));
    }

    @Test
    void findAllBooksPaginategAndSorted() {
    }

    @Test
    @DisplayName("Should addBook - Not null value")
    void saveBook() {
        given(bookRepoMock.save(anyObject())).willReturn(new Book());

        BookAddDto bookAddDto = new BookAddDto();

        assertThat(bookService.addBook(bookAddDto), is(notNullValue()));
    }

    @Test
    @DisplayName("Should findBookById - Success")
    void findBookById() {
        given(bookRepoMock.findByBookId(Mockito.anyLong())).willReturn(bookInit1);

        Book result = bookService.findBookById(1L);

        assertThat(result, equalTo(bookInit1));
    }

    @Test
    @DisplayName("Should findBookById - Not found")
    void findBookByIdNotFound() {
        Book result = bookService.findBookById(1L);

        assertThat(result, nullValue());
    }
}