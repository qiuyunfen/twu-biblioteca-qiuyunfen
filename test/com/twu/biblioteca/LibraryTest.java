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
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Test-driven Development: By Example", "Kent Beck", 2003));
        assertEquals(library.getBooksList(books), books);
    }

    @After
    public void tearDown() {
        library = null;
    }
}
