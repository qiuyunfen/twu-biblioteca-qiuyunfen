package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaLib {
    static HashMap<String, String> HINT_INFO = new HashMap<String, String>();
    static HashMap<String, Integer> STATUS = new HashMap<String, Integer>();
    User user;

    public String handleInput(String input) {

        return "";
    }
    public void init(String name, String role) {
        user = userSignIn(name, role);
        ArrayList<Book> books = initBooksList();
        initHintInfo();
        initStatus();
        librarySystem(books);
    }

    public void initHintInfo() {
        HINT_INFO.put("WELCOME_MESSAGE", ",Welcome to Biblioteca Library");
        HINT_INFO.put("MAIN_COMMAND", "1.List Books");
        HINT_INFO.put("BOOK_COMMAND", "1.check out book\n2.return book");
        HINT_INFO.put("CHECK_OUT_SUCCESS", "Thank you! Enjoy the book");
        HINT_INFO.put("CHECK_OUT_FAIL", "That book is not available.");
        HINT_INFO.put("RETURN_SUCCESS", "Thank you for returning the book.");
        HINT_INFO.put("RETURN_FAIL", "That is not a valid book to return.");
    }

    public void initStatus() {
        STATUS.put("MAIN_DEFAULT", 0);
        STATUS.put("LIST_BOOKS", 1);
        STATUS.put("CHECK_OUT_BOOK", 11);
        STATUS.put("RETURN_BOOK", 12);
    }

    public ArrayList<Book> initBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Test-driven Development: By Example", "Kent Beck", 2003, "Lucy"));
        books.add(new Book(2,"Head First Java", "Sierra k", 2007, ""));
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
            if(book.getCheckUserName().equals("")) {
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
            if(book.getCheckUserName().equals(user.getName())) {
                userListBooks.add(book);
                System.out.println(book.getBookName()  + " " + book.getAuthor() + " " + book.getYear());
            }
        }
        return userListBooks;
    }

    public String checkoutBook(int bookId, ArrayList<Book> unCheckoutBooks) {
        for(Book book: unCheckoutBooks) {
            if(bookId == book.getBookId()) {
                book.setCheckUserName(user.getName());
                return HINT_INFO.get("CHECK_OUT_SUCCESS");
            }
        }
        return HINT_INFO.get("CHECK_OUT_FAIL");
    }

    public String returnBook(int bookId, ArrayList<Book> checkoutBooks) {
        for(Book book: checkoutBooks) {
            if(book.getBookId() == bookId) {
                book.setCheckUserName("");
                return HINT_INFO.get("RETURN_SUCCESS");
            }
        }
        return HINT_INFO.get("RETURN_FAIL");
    }

    public String printWelcomeMsg() {
        System.out.println(user.getName() + HINT_INFO.get("WELCOME_MESSAGE"));
        return user.getName() + HINT_INFO.get("WELCOME_MESSAGE");
    }

    public User userSignIn(String name, String role) {
       User user = new User(name, role);
       return user;
    }
}
