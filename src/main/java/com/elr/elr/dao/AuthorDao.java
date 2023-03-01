package com.elr.elr.dao;

import com.elr.elr.domain.Author;

public interface AuthorDao {
    //Author CRUD Operations
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}