package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    BibliotecaLib library;

    @Before
    public void setUp() {
       library = new BibliotecaLib();
       library.userSignIn("Lucy", "customer");
       library.init();
    }
    @Test
    public void should_return_user_welcome_message() {
        assertEquals(library.printWelcomeMsg(), "Lucy,Welcome to Biblioteca Library\n1.List Books");
    }

    @Test
    public void should_return_uncheck_out_books_list() {
        assertEquals(library.getUnCheckOutBooksList(), "List Books:\nHead First Java Sierra k 2007\n");
    }

    @Test
    public void should_return_checkout_book_list() {
        assertEquals(library.getCheckoutBooksList(), "The books you have checked out:\nTest-driven Development: By Example Kent Beck 2003\n");
    }

    @Test
    public void should_return_checkout_success() {
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        userListBooks.add(new Book(2, "Head First Java", "Sierra k", 2007, ""));
        assertEquals(library.checkoutBook(2, userListBooks), "Thank you! Enjoy the book");
    }

    @Test
    public void should_return_checkout_fail() {
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        userListBooks.add(new Book(2, "Head First Java", "Sierra k", 2007, ""));
        assertEquals(library.checkoutBook(3, userListBooks), "That book is not available.");
    }

    @Test
    public void should_return_return_book_success() {
        ArrayList<Book> userCheckoutListBooks = new ArrayList<Book>();
        userCheckoutListBooks.add(new Book(2, "Head First Java", "Sierra k", 2007, "Lucy"));
        assertEquals(library.returnBook(2, userCheckoutListBooks), "Thank you for returning the book.");
    }

    @Test
    public void should_return_return_book_fail() {
        ArrayList<Book> userCheckoutListBooks = new ArrayList<Book>();
        userCheckoutListBooks.add(new Book(2, "Head First Java", "Sierra k", 2007, "Lucy"));
        assertEquals(library.returnBook(3, userCheckoutListBooks), "That is not a valid book to return.");
    }

    @After
    public void tearDown() {
        library = null;
    }
}
