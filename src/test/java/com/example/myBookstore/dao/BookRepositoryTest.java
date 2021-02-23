package com.example.myBookstore.dao;

import com.example.myBookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookRepositoryTest {

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
    @DisplayName("Should findByBookId Success")
    void findByBookId() {
        entityManager.persist(bookInint);
        entityManager.flush();

        Book result = bookRepository.findByBookId(1L);

        assertThat(result.getBookId(), is(bookInint.getBookId()));
    }

    @Test
    @DisplayName("Should findByBookId - Not found")
    void findByBookIdNotFound() {
        Book result = bookRepository.findByBookId(1L);

        assertThat(result, nullValue());
    }
}
