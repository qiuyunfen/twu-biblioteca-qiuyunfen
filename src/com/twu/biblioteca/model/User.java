package com.twu.biblioteca.model;

public class User {
    protected String name;
    protected String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public User() {}
}
