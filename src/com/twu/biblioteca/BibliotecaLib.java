package com.twu.biblioteca;

import com.twu.biblioteca.model.User;

public class BibliotecaLib {
    public void init(String name, String role) {
        User user = new User(name, role);
    }
}
