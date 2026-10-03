	package com.example.demo.dto;
	
	import java.time.LocalDate;
	
	public class LoanDTO {
	
	    private Long id;
	    private String username;
	    private String bookTitle;
	    private LocalDate borrowDate;
	    private LocalDate returnDate;
	
	    // Getter ve Setter
	
	    public Long getId() {
	        return id;
	    }
	
	    public void setId(Long id) {
	        this.id = id;
	    }
	
	    public String getUsername() {
	        return username;
	    }
	
	    public void setUsername(String username) {
	        this.username = username;
	    }
	
	    public String getBookTitle() {
	        return bookTitle;
	    }
	
	    public void setBookTitle(String bookTitle) {
	        this.bookTitle = bookTitle;
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
	}
