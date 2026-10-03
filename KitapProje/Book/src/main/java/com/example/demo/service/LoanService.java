package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    
    
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public Loan borrowBook(User user, Long bookId) {
    	Book book = bookRepository.findById(bookId)
    		    .orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı: " + bookId));
    	if (!book.isAvailable()) {
            throw new RuntimeException("Kitap şu anda uygun değil.");
        }
        boolean alreadyBorrowed = loanRepository.findByUser(user).stream()
        	    .anyMatch(l -> l.getBook().getId().equals(bookId) && l.getReturnDate() == null);
        	if (alreadyBorrowed) {
        	    throw new RuntimeException("Kitap zaten ödünç alınmış.");
        	}

        		
        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowDate(LocalDate.now()); // 🟢 Bu kesinlikle olmalı

        return loanRepository.save(loan);
    }


    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getLoansByUser(User user) {
        return loanRepository.findByUser(user);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
