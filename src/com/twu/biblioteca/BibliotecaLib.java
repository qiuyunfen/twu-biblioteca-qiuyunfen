package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaLib {
    public HashMap<String, String> HINT_INFO = new HashMap<String, String>();
    public HashMap<String, Integer> STATUS = new HashMap<String, Integer>();
    public static int status ;
    public User user;
    public ArrayList<Book> books;

    public void init() {
        initHintInfo();
        initStatus();
        books = initBooksList();
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
        STATUS.put("MAIN_DEFAULT", -1);
        STATUS.put("SIGN_IN", 0);
        STATUS.put("LIST_BOOKS", 1);
        STATUS.put("CHECK_OUT_BOOK", 11);
        STATUS.put("RETURN_BOOK", 12);
        status = STATUS.get("MAIN_DEFAULT");
    }

    public String handleInput(String input) {

        if(status == STATUS.get("MAIN_DEFAULT")) {
            status = STATUS.get("SIGN_IN");
            return handleUserSignIn(input);
        } else if(status == STATUS.get("SIGN_IN")) {
            status = STATUS.get("LIST_BOOKS");
            return getUnCheckOutBooksList();
        }
        return "";
    }
    public ArrayList<Book> initBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Test-driven Development: By Example", "Kent Beck", 2003, "Lucy"));
        books.add(new Book(2,"Head First Java", "Sierra k", 2007, ""));
        return books;
    }

    public String getUnCheckOutBooksList() {
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        String msg = "List Books:\n";
        for(Book book : books) {
            if(book.getCheckUserName().equals("")) {
                userListBooks.add(book);
                msg += book.getBookName()  + " " + book.getAuthor() + " " + book.getYear();
            }
        }
        return msg;
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
        return user.getName() + HINT_INFO.get("WELCOME_MESSAGE") + "\n" + HINT_INFO.get("MAIN_COMMAND");
    }

    public User userSignIn(String name, String role) {
       user = new User(name, role);
       return user;
    }

    public String handleUserSignIn(String input) {
        String[] userInfo = input.split(",");
        user = userSignIn(userInfo[0], userInfo[1]);
        return printWelcomeMsg();
    }
}
