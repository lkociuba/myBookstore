/*
package com.example.myBookstore.repository;

import com.example.myBookstore.MyBookstoreApplication;
import com.example.myBookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.example.myBookstore.repository")
class BookRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(MyBookstoreApplication.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Book bookInint;

    @BeforeEach
    void init(){
        bookInint = new Book();
        bookInint.setName("Reed book");
        bookInint.setDescription("Red book about reed things");
        bookInint.setPrice(29.99);
    }

    @Test
    void shouldFindBookById(){
        entityManager.persist(bookInint);
        entityManager.flush();

        Book result = bookRepository.findBookById(1L);

        assertThat(result.getBookId(), is(bookInint.getBookId()));
    }

    @Test
    void shouldFindBookByName(){
        entityManager.persist(bookInint);
        entityManager.flush();

        Book result = bookRepository.findBookByName("Reed book");

        assertThat(result.getName(), is(bookInint.getName()));
    }

    @Test
    void shouldRetrieveAllBook() {
        List<Book> books = bookRepository.retrieveAllBooks();

        logger.info("All books -> {}", books);
    }
}

 */