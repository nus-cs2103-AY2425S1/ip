package hien.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the task management application.
 * This class handles all input/output operations, including displaying messages
 * to the user and reading user input.
 */
public class UI {
    private Scanner scanner;
    private String lastMessage;

    /**
     * Constructs a new UI object and initializes the scanner for reading user input.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        String welcomeMessage = " Hello! I'm Hien\n"
                                + " What can I do for you?\n";

        lastMessage = welcomeMessage;
        return welcomeMessage;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbye() {
        String goodbyeMessage = " Bye. Hope to see you again soon!";
        lastMessage = goodbyeMessage;
        return goodbyeMessage;
    }

    /**
     * Displays a horizontal line for formatting purposes.
     */
    public String showLine() {
        String lineMessage = "____________________________________________________________";
        return lineMessage + "\n";
    }

    /**
     * Reads a command from the user input.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public String showLoadingError() {
        String errorMessage = "Error loading tasks. Starting with an empty task list.";
        lastMessage = errorMessage;
        return errorMessage + "\n";
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        lastMessage = message;
        return message + "\n";
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public String showMessage(String message) {
        lastMessage = message;
        return message + "\n";
    }

    /**
     * Closes the scanner when it's no longer needed.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Retrieves the last message that was displayed to the user.
     * This method is primarily used for testing purposes.
     *
     * @return The last message that was displayed.
     */
    public String getLastMessage() {
        return lastMessage;
    }
}
