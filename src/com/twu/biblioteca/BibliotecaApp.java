package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String role = sc.nextLine();
        BibliotecaLib library = new BibliotecaLib();
        library.init(name, role);
    }

}
