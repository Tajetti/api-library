package com.api.biblioteca.model.service;

import com.api.biblioteca.model.entity.Book;
import com.api.biblioteca.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public String createBook(Book book) {
        repository.save(book);
        return "Book created successfully!";
    }

    public List<Book> Books() {
        Iterable<Book> books = repository.findAll();
        return (List<Book>) books;
    }

    public Book updateBook(Long id, Book newBook){
        Book existingBook = repository.findBookById(id);

        if(existingBook == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        if(newBook.getTitle() != null){
            existingBook.setTitle(newBook.getTitle());
        }

        if(newBook.getDescription() != null){
            existingBook.setDescription(newBook.getDescription());
        }

        if(newBook.getAuthor() != null){
            existingBook.setAuthor(newBook.getAuthor());
        }

        if (newBook.getAuthor() != null && !newBook.getAuthor().isEmpty()) {
            existingBook.setAuthor(newBook.getAuthor());
        }

        repository.save(existingBook);
        return existingBook;
    }

    public String deleteBook(Long id) throws Exception{
        try{
            repository.deleteById(id);
            return "Book deleted successfully!";
        } catch (Exception e){
            throw new Exception("Something went wrong: " + e.getMessage());
        }
    }
}
