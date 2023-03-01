package com.elr.elr.repositories;


import com.elr.elr.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

public interface BookRepository extends JpaRepository<Book, Long> {
    //Optional Return Type
    Optional<Book>  findBookByTitle(String title);

    //Native SQL Queries
    @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
    Book findBookByTitleNativeQuery(@Param("title") String title);

    //Named Parameters with @Query
    @Query("SELECT b FROM Book b where b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);

    //JPA Named Queries
    Book jpaNamed(@Param("title") String title);

    //Declaring Queries Using @Query
    @Query("SELECT b FROM Book b where b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    Book readByTitle(String title);

    // Null Handling
    @Nullable
    Book getByTitle(@Nullable String title);

    //Stream Query Results
    Stream<Book> findAllByTitleNotNull();

    //Asynchronous Query Results
    @Async
    Future<Book> queryByTitle(String title);
}