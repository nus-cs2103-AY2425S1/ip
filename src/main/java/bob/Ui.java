package bob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
    public void showWelcome() {
        System.out.println("Hello! I'm Bob the bot!\nHow can I help you?");
    }

    /**
     * Displays an error message to the user.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays a message to the user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a success message to indicate successful loading of saved tasks.
     */
    public void showLoadingSuccess() {
        System.out.println("Saved tasks has been successfully loaded.");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again :)");
    }

    /**
     * Displays a message to indicate user's list of tasks is empty.
     */
    public void showNoTasks() {
        System.out.println("There are 0 tasks in your list now. Start adding them!");
    }
}
