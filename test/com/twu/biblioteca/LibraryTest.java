package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryThing;
import com.twu.biblioteca.model.Movie;
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
       library.init();
       library.handleUserSignIn("Lucy");
    }
    @Test
    public void should_return_user_welcome_message() {
        assertEquals("Lucy,Welcome to Biblioteca Library\n", library.printWelcomeMsg());
    }

    @Test
    public void should_return_uncheck_out_books_list() {
        assertEquals("List Books:\n2:bookName:Head First Java,author:Sierra k,year:2007\n", library.getUnCheckOutBooksList());
    }

    @Test
    public void should_return_checkout_book_list() {
        assertEquals("The books you have checked out:\n1:bookName:Test-driven Development: By Example,author:Kent Beck,year:2003\n",library.getCheckoutBooksList());
    }

    @Test
    public void should_return_checkout_success() {
        assertEquals("Thank you! Enjoy!\n",library.checkoutBook(2));
    }

    @Test
    public void should_return_checkout_fail() {
        assertEquals("That id is not available.\n",library.checkoutBook(3));
    }

    @Test
    public void should_return_return_book_success() {
        assertEquals("Thank you for returning.\n",library.returnBook(1));
    }

    @Test
    public void should_return_return_book_fail() {
        assertEquals("That is not a valid id to return.\n", library.returnBook(3));
    }

    @Test
    public void should_return_list_movie() {
        assertEquals("List Movies:\n1:movieName:Wonder Woman,director:Patty Jenkins,year:2017,rating:7.3\n", library.getlistMovies());
    }

    @Test
    public void should_return_checkout_movie_success() {
        assertEquals("Thank you! Enjoy!\n", library.checkoutMovie(1));
    }

    @Test
    public void should_retrurn_checkout_movie_fail() {
        assertEquals("That id is not available.\n", library.checkoutMovie(2));
    }

    @Test
    public void should_return_return_fail() {
        assertEquals("That is not a valid id to return.\n", library.returnMovie(1));
    }

    @Test
    public void should_return_user_information() {
        assertEquals("Lucy,531802979@qq.com,swust,18980131432\n",library.displayUserInfo());
    }
    @After
    public void tearDown() {
        library = null;
    }
}
