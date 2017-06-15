package com.twu.biblioteca.model;

import java.util.ArrayList;

public class LibraryThing {
    public int id;
    public String name;
    public int year;
    public ArrayList<String> checkOutUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getCheckOutUser() {
        return checkOutUser;
    }

    public void setCheckOutUser(ArrayList<String> checkOutUser) {
        this.checkOutUser = checkOutUser;
    }

    public LibraryThing(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.checkOutUser = new ArrayList<String>();
    }

    public LibraryThing() {}
}
