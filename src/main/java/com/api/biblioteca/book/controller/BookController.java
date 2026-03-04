package com.api.biblioteca.book.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.biblioteca.book.dto.BookResponseDTO;
import com.api.biblioteca.book.repository.BookRepository;
import com.api.biblioteca.book.service.BookService;




@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService service;

    public BookController(BookRepository repository) {
        this.service = new BookService(repository);
    }

    @GetMapping
    public List<BookResponseDTO> getAll() {
        List<BookResponseDTO> books = service.findAll();
        
        if(books == null || books.isEmpty()) {
            throw new RuntimeException("Não há livros cadastrados");
        }
        
        return books;
    }

    @GetMapping("/{id}")
    public BookResponseDTO getById(@PathVariable UUID id) {
        BookResponseDTO book = service.findById(id);
        
        if(book == null) {
            throw new RuntimeException("Livro não encontrado");
        }
        
        return book;
    }
    
    @GetMapping("/{title}")
    public List<BookResponseDTO> findByTitle(@PathVariable String title) {
        List<BookResponseDTO> books = service.findByTitle(title);
        
        if(books == null || books.isEmpty()) {
            throw new RuntimeException("Livro não encontrado");
        }
        
        return books;
    }
    
}
