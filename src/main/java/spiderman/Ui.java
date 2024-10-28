package spiderman;

import java.util.Scanner;

/**
 * Handles user interactions, including input and output in the console.
 */
public class Ui {
    private final Scanner scan;

    /**
     * Constructs an Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        this.scan = new Scanner(System.in);  // Initialize Scanner in the constructor
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays an error message when there is an issue loading data from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading data from file. Starting with an empty task list.");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Displays a divider line to separate sections of output.
     */
    public void showDividerLine() {
        System.out.println("=======================================================================================");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Closes the Scanner to release the resources associated with it.
     */
    public void close() {
        scan.close();
    }
}
