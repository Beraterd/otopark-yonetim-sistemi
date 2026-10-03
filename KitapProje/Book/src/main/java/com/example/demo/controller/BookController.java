package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book addBook(@RequestBody BookDTO dto) {
        return bookService.addBook(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
