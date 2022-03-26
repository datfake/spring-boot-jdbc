package com.datngo.springbootjdbc.controller;

import com.datngo.springbootjdbc.model.Book;
import com.datngo.springbootjdbc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        try {
            bookRepository.save(Book.builder().name(book.getName()).description(book.getDescription()).build());
            return new ResponseEntity<>("Book was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Book _book = bookRepository.findById(id);
        if (_book != null) {
            _book.setId(id);
            _book.setName(book.getName());
            _book.setDescription(book.getDescription());
            bookRepository.update(_book);
            return new ResponseEntity<>("Book was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Book with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        try {
            int result = bookRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Book with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Book was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete Book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
