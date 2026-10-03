package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setAvailable(true);
        return bookRepository.save(book);
    }


    public Book updateBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı: " + id));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
