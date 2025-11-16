package com.api.biblioteca.model.repository;

import com.api.biblioteca.model.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);
}
