package com.example.myBookstore.repository;

import com.example.myBookstore.MyBookstoreApplication;
import com.example.myBookstore.dao.BookRepository;
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
    void init() {
        bookInint = new Book();
        bookInint.setName("Reed book");
        bookInint.setDescription("Red book about reed things");
        bookInint.setPrice(29.99);
    }

    @Test
    void shouldFindBookById() {
        entityManager.persist(bookInint);
        entityManager.flush();

        Book result = bookRepository.findByBookId(1L);

        assertThat(result.getBookId(), is(bookInint.getBookId()));
    }
}
