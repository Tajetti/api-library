package com.api.biblioteca.controller;

import com.api.biblioteca.model.entity.Book;
import com.api.biblioteca.model.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService service) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return ResponseEntity.status(201).body("Book created!");
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.Books();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id,
                                            @RequestBody Book book) throws  Exception {
        Book existingBook = bookService.updateBook(id, book);
        return  ResponseEntity.ok(existingBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return  ResponseEntity.status(400).body("Error deleting book!" + e.getMessage());
        }
    }
}
