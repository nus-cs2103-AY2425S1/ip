package ava.tui;

import java.util.Scanner;

import ava.AVA;


/**
 *  Provides a Text User Interface for AVA.
 */
public class TextUI {
    //TODO: add verbose toggle

    /**
     * Provides a Text User Interface.
     * <br>
     * This is the main function which keeps the interaction running between user and AVA.
     * <br>
     * The model is separated into its own class for OOP.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        AVA ava = new AVA(); //default ava.ava object

        // Greet the user
        System.out.println(ava.welcomeUser());

        ava.tellAva(scanner.nextLine());
        // Process user input until user says bye
        while (ava.isRunning()) {
            ava.respond(System.out);
            ava.tellAva(scanner.nextLine());
        }
        //Exit
        System.out.println(ava.bye());

    }

    /**
     *  Runs AVA.
     *  <br>
     *  Main driver method running AVA.
     */
    public static void main(String[] args) {
        run();
    }
}
