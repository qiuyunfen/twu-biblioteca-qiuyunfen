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
        library.init("Lucy", "customer");
    }
    @Test
    public void should_return_user_welcome_message() {
        assertEquals(library.printWelcomeMsg(), "Lucy,Welcome to Biblioteca Library");
    }

    @Test
    public void should_return_all_books_list() {
        ArrayList<Book> books = library.initBooksList();
        assertEquals(library.getUnCheckOutBooksList(books), books);
    }

    @Test
    public void should_return_uncheck_out_books_list() {
        ArrayList<Book> books = library.initBooksList();

        ArrayList<Book> userListBooks = new ArrayList<Book>();
        userListBooks.add(new Book("Head First Java", "Sierra k", 2007, false));

        assertEquals(library.getUnCheckOutBooksList(books).size(), userListBooks.size());
    }

    @Test
    public void should_return_checkout_book_list() {
        ArrayList<Book> books = library.initBooksList();
        ArrayList<Book> userListBooks = new ArrayList<Book>();
        userListBooks.add(new Book("Head First Java", "Sierra k", 2007, false));

        assertEquals(library.getCheckoutBooksList(books).size(), userListBooks.size());
    }

    @After
    public void tearDown() {
        library = null;
    }
}
