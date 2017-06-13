package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaLib library = new BibliotecaLib();
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String role = sc.nextLine();
        library.init(name, role);


    }
}
