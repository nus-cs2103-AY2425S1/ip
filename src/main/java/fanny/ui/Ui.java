package fanny.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user by printing messages and reading user input.
 */
public class Ui {

    /** Scanner object for reading user input from the console. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message when the application starts.
     */
    public void printHello() {
        showHorizontalLine();
        System.out.println("Hello! I'm Fanny\nWhat can I do for you?");
        showHorizontalLine();
    }

    /**
     * Prints a goodbye message when the application ends.
     */
    public void printBye() {
        showHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        showHorizontalLine();
    }

    /**
     * Reads and returns the user's input from the console.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        System.out.print("User:");
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the console for separating sections of output.
     */
    public void showHorizontalLine() {
        System.out.println("_____________________________________________");
    }

    /**
     * Prints a custom message to the console.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner, releasing any resources associated with it.
     */
    public void close() {
        scanner.close();
    }
}