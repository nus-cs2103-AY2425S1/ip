package util;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It is responsible for reading user input and displaying messages to the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println("---------------");
        System.out.println("Hello I am Schedulo!");
        System.out.println("What can I do for you?");
        System.out.println("---------------");
    }

    /**
     * Displays a horizontal line to the user.
     * This is used to separate different sections of output for better readability.
     */
    public void showLine() {
        System.out.println("---------------");
    }

    /**
     * Displays an error message when there is an issue loading the task file.
     */
    public void showLoadingError() {
        System.out.println("File not found, please create a new file data.txt under data folder outside src");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner used for user input.
     * This should be called when the application is shutting down.
     */
    public void close() {
        sc.close();
    }
}
