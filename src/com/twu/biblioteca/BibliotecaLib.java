package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;

public class BibliotecaLib {
    static  final String WELCOME_MESSAGE = ",Welcome to Biblioteca Library";
    User user;

    public void init(String name, String role) {
        user = userSignIn(name, role);
        ArrayList<Book> books = initBooksList();
        librarySystem(books);
    }

    public ArrayList<Book> initBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Test-driven Development: By Example", "Kent Beck", 2003, true));
        books.add(new Book("Head First Java", "Sierra k", 2007, false));
        return books;
    }

    public void librarySystem(ArrayList<Book> books) {
        printWelcomeMsg();
        getUnCheckOutBooksList(books);
        getCheckoutBooksList(books);
    }

    public ArrayList<Book> getUnCheckOutBooksList(ArrayList<Book> books) {
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        System.out.println("list books");
        for(Book book : books) {
            if(!book.isCheckOut()) {
                userListBooks.add(book);
                System.out.println(book.getBookName()  + " " + book.getAuthor() + " " + book.getYear());
            }
        }
        return userListBooks;
    }

    public ArrayList<Book> getCheckoutBooksList(ArrayList<Book> books) {
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        System.out.println("the books you have checked out");
        for(Book book : books) {
            if(book.isCheckOut()) {
                userListBooks.add(book);
                System.out.println(book.getBookName()  + " " + book.getAuthor() + " " + book.getYear());
            }
        }
        return userListBooks;
    }

    public String printWelcomeMsg() {
        System.out.println(user.getName() + WELCOME_MESSAGE);
        return user.getName() + WELCOME_MESSAGE;
    }

    public User userSignIn(String name, String role) {
       User user = new User(name, role);
       return user;
    }
}
