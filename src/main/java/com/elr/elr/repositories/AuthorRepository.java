package com.elr.elr.repositories;


import com.elr.elr.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
            //Query Methods
            Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
