package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserSignInTest {
    User user;

    @Before
    public void setUp() {
        user = new User("Lucy");
    }
    @Test
    public void should_return_customer_name() {
        assertEquals("Lucy", user.getName());
    }

    @After
    public void tearDown() {
        user = null;
    }
}
