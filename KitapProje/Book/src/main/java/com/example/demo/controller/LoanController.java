package com.example.demo.controller;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.service.LoanService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @PostMapping("/{bookId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Loan borrowBook(@PathVariable Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        return loanService.borrowBook(user, bookId);
    }

    @PutMapping("/{loanId}")
    @PreAuthorize("hasRole('USER')")
    public Loan returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<Loan> myLoans(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        return loanService.getLoansByUser(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Loan> allLoans() {
        return loanService.getAllLoans();
    }
}
