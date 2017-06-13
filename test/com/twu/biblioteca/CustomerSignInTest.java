package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerSignInTest {
    Customer customer;

    @Test
    public void setUp() {
        customer = new Customer("Lucy", "customer");
    }
    @Test
    public void should_return_customer_name() {
        assertEquals("Lucy", customer.getName());
    }

    @Test
    public void tearDown() {
        customer = null;
    }
}
