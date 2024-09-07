package bob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the user interface for interacting with Bob chatbot.
 * The Ui class interacts with the user through the console.
 * It provides methods to read user input, display messages and show error messages.
 */
public class Ui {
    private final BufferedReader reader;

    /**
     * Initialises a new Ui instance with a BufferedReader for reading user input.
     */
    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads user input.
     * This method reads a line of input from the console, trims any leading or trailing whitespace,
     * and returns the resulting string.
     *
     * @return User input as a string.
     * @throws BobException If an error occurs while reading input.
     */
    public String readCommand() throws BobException {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            throw new BobException("An error occurred while reading input: " + e.getMessage());
        }
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm Bob the bot!\nHow can I help you?";
    }

    /**
     * Displays an error message to the user.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Displays a message to the user.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays a success message to indicate successful loading of saved tasks.
     */
    public String showLoadingSuccess() {
        return "Saved tasks are successfully loaded.";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again :)";
    }

    /**
     * Displays a message to indicate user's list of tasks is empty.
     */
    public String showNoTasks() {
        return "There are 0 tasks in your list now. Start adding them!";
    }
}
