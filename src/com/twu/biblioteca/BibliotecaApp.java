package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaLib library = new BibliotecaLib();
        library.init();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String msg = library.handleInput(input);
            System.out.println(msg);
        }
    }
}
