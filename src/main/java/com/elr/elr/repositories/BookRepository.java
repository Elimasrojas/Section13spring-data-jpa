package com.elr.elr.repositories;


import com.elr.elr.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.lang.Nullable;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book>  findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);
}