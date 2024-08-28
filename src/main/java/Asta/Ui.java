package Asta;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user.
 * It provides methods to read user input, display messages, show errors, and handle other user interface elements.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return A string representing the user's command input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Asta.Asta\nWhat can I do for you?");
    }

    /**
     * Displays a divider line for better readability in the console output.
     */
    public void showLine() {
        System.out.println("__________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message indicating that there was a problem loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
