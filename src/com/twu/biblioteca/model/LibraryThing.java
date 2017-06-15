package com.twu.biblioteca.model;

public interface LibraryThing {
    public int id = 0;
    public String name = "";
    public int year = 0;

    public String checkOut();
    public String returnThing();
}
