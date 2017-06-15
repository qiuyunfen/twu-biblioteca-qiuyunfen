package com.twu.biblioteca.model;

public class Book extends LibraryThing{
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public Book(int bookId, String bookName, String author, int year) {
        super(bookId, bookName, year);
        this.author = author;
    }
    public Book(){}

}
