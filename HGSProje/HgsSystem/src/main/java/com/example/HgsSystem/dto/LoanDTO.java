package com.example.HgsSystem.dto;

import java.time.LocalDate;
import java.util.Objects;

public class LoanDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // toString, equals, hashCode metodları
    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDTO loanDTO = (LoanDTO) o;
        return id.equals(loanDTO.id) &&
                userId.equals(loanDTO.userId) &&
                bookId.equals(loanDTO.bookId) &&
                borrowDate.equals(loanDTO.borrowDate) &&
                returnDate.equals(loanDTO.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId, borrowDate, returnDate);
    }
}
