package com.twu.biblioteca.model;

public class Book {
    private String bookName;
    private String author;
    private int year;
    private String checkUserName;

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

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public Book(String bookName, String author, int year, String checkUserName) {
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.checkUserName = checkUserName;
    }
    public Book(){}

}
