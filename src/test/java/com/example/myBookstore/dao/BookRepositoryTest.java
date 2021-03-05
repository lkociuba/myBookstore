package com.example.myBookstore.dao;

import com.example.myBookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Book bookInint1;

    @BeforeEach
    void init() {
        bookInint1 = new Book();
        bookInint1.setName("Reed book");
        bookInint1.setDescription("Red book about reed things");
        bookInint1.setPrice(29.99);
    }

    @Test
    @DisplayName("Should findByBookId - Success")
    void findByBookId() {
        entityManager.persist(bookInint1);
        entityManager.flush();

        Book result = bookRepository.findByBookId(1L);

        assertThat(result.getBookId(), is(bookInint1.getBookId()));
    }

    @Test
    @DisplayName("Should findByBookId - Not found")
    void findByBookIdNotFound() {
        Book result = bookRepository.findByBookId(1L);

        assertThat(result, nullValue());
    }
}
