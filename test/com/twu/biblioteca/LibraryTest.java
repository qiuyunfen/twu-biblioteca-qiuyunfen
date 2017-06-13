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
       library.userSignIn("Lucy");
       library.init();
    }
    @Test
    public void should_return_user_welcome_message() {
        assertEquals(library.printWelcomeMsg(), "Lucy,Welcome to Biblioteca Library\n");
    }

    @Test
    public void should_return_uncheck_out_books_list() {
        assertEquals(library.getUnCheckOutBooksList(), "List Books:\n2:bookName:Head First Java,author:Sierra k,year:2007\n");
    }

    @Test
    public void should_return_checkout_book_list() {
        assertEquals(library.getCheckoutBooksList(), "The books you have checked out:\n1:bookName:Test-driven Development: By Example,author:Kent Beck,year:2003\n");
    }

    @Test
    public void should_return_checkout_success() {
        assertEquals(library.checkoutBook(2), "Thank you! Enjoy the book\n");
    }

    @Test
    public void should_return_checkout_fail() {
        assertEquals(library.checkoutBook(3), "That book is not available.\n");
    }

    @Test
    public void should_return_return_book_success() {
        assertEquals(library.returnBook(1), "Thank you for returning the book.\n");
    }

    @Test
    public void should_return_return_book_fail() {
        assertEquals(library.returnBook(3), "That is not a valid book to return.\n");
    }

    @After
    public void tearDown() {
        library = null;
    }
}
