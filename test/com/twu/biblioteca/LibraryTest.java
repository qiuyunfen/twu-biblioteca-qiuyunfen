package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LibraryTest {
    BibliotecaLib library;

    @Before
    public void setUp() {
       library = new BibliotecaLib();
    }
    @Test
    public void should_return_user_welcome_message() {
        library.init("Lucy", "customer");
        assertEquals(library.printWelcomeMsg(), "Lucy,Welcome to Biblioteca Library");
    }
    @After
    public void tearDown() {
        library = null;
    }
}
