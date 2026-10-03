package com.example.HgsSystem.dto;

import java.util.Objects;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private boolean available;

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // toString, equals, hashCode metodları
    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return available == bookDTO.available && 
               Objects.equals(id, bookDTO.id) && 
               Objects.equals(title, bookDTO.title) && 
               Objects.equals(author, bookDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, available);
    }
}
