package yoda;

import java.util.Scanner;

/**
 * Handles the user interface interactions, including input and output operations.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the UI with a new Scanner object for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The line of input entered by the user, trimmed of leading and trailing whitespace.
     */
    public String getNextLine() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a separator line to the console.
     */
    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays an error message indicating a problem with loading the UI.
     */
    public void showLoadingError() {
        String message = "Error loading UI";
        System.out.println("Error loading UI");
    }
}
