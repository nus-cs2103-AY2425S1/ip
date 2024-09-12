package carly.ui;

import java.util.Scanner;

import carly.exception.CarlyException;


/**
 * Deals with interactions with the user.
 * Also deals with formatting of output message.
 */
public class Ui {
    public static final String INDENTATION = "    ";
    private final String username;

    public Ui(String username) {
        this.username = username;
    }

    public Ui() {
        this("");
    }

    public Ui setUsername(Scanner scan) {
        printOutput("What is your name?");
        String username = scan.nextLine();
        return new Ui(username);
    }

    /** Displays a welcome message to the user with the current username. */
    public String welcomeMsg() {
        return ("Hey " + username + "! I'm Carly.\n" + "What can I do for you?");
    }

    /** Displays a farewell message to the user with the current username. */
    public String byeMsg() {
        return ("Bye " + username + ". I'll see you next time!");
    }

    public static void printOutput(String message) {
        System.out.println(INDENTATION + message);
    }

}

