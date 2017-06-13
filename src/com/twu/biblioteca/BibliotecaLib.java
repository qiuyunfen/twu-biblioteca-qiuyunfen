package com.twu.biblioteca;

import com.twu.biblioteca.model.User;

public class BibliotecaLib {
    static  final String WELCOME_MESSAGE = ",Welcome to Biblioteca Library";
    User user;

    public void init(String name, String role) {
        user = userSignIn(name, role);
        printWelcomeMsg();
    }

    public String printWelcomeMsg() {
        System.out.print(user.getName() + WELCOME_MESSAGE);
        return user.getName() + WELCOME_MESSAGE;
    }

    public User userSignIn(String name, String role) {
       User user = new User(name, role);
       return user;
    }
}
