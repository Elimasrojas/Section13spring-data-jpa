package com.elr.elr;

import com.elr.elr.domain.Book;
import com.elr.elr.repositories.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    ////JPA Named Queries
    @Test
    void testBookJPANamedQuery() {
        Book book = bookRepository.jpaNamed("Clean Code");
        assertThat(book).isNotNull();
    }

    //Native SQL Queries
    @Test
    void testBookQueryNative() {
        Book book = bookRepository.findBookByTitleNativeQuery("Clean Code");
        assertThat(book).isNotNull();
    }

    //Named Parameters with @Query
    @Test
    void testBookQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");
        assertThat(book).isNotNull();
    }


    ////Declaring Queries Using @Query
    @Test
    void testBookQuery() {
        Book book = bookRepository.findBookByTitleWithQuery("Clean Code");

        assertThat(book).isNotNull();
    }


    //Asynchronous Query Results
    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture=bookRepository.queryByTitle("Clean Code");

        Book book=bookFuture.get();

        assertNotNull(book);


    }
    //Stream Query Results
    @Test
    void testBookStream() {
        AtomicInteger count = new AtomicInteger();

        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });

        assertThat(count.get()).isGreaterThan(4);
    }

    @Test
    void testEmptyResultException() {

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepository.readByTitle("foobar4");
        });
    }
    // // Null Handling
    @Test
    void testNullParam() {
        assertNull(bookRepository.getByTitle(null));
    }

    // // Null Handling
    @Test
    void testNoException() {

        assertNull(bookRepository.getByTitle("foo"));
    }
}