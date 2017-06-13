package com.twu.biblioteca.model;

public class Book {
    private String bookName;
    private String author;
    private int year;
    private boolean isCheckOut;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public Book(String bookName, String author, int year, boolean isCheckOut) {
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.isCheckOut = isCheckOut;
    }
    public Book(){}
}
