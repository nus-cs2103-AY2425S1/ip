package asta.ui;

/**
 * The Ui class handles interactions with the user. It provides methods to read user input, display messages, show
 * errors, and handle other user interface elements.
 */
public class Ui {

    /**
     * Constructs a Ui object and initializes the Scanner for reading user input.
     */
    public Ui() {
    }

    /**
     * Displays the welcome message to the user when the application starts.
     */
    public String showWelcome() {
        return "Hello! I'm Asta.Asta\nWhat can I do for you?";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to display.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays an error message indicating that there was a problem loading tasks from the file.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
}
