package repsmax.ui;

import repsmax.model.Task;

import java.util.List;

/**
 * Handles all user interactions in the application.
 * <p>
 * The {@code Ui} class is responsible for managing user input and output.
 * It provides methods for reading user commands, displaying messages,
 * and showing various informational prompts.
 * </p>
 */
public class Ui {
    private StringBuilder output;

    /**
     * Constructs a {@code Ui} object and initializes the scanner for reading
     * user input.
     */
    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the input line entered by the user.
     */
    public void readCommand() {

    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        output.append("Hello! I'm Repsmax\nWhat can I do for you?\n");
    }

    /**
     * Displays a goodbye message to the user.
     * <p>
     * The message bids farewell and expresses hope to see the user again
     * soon.
     * </p>
     */
    public String showGoodbye() {
        return "  ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "  ____________________________________________________________\n";
    }

    /**
     * Displays an error message when loading tasks fails.
     * <p>
     * The message indicates that an error occurred while loading tasks
     * from storage.
     * </p>
     */
    public void showLoadingError() {
        output.append("An error occurred while loading tasks.\n");
    }

    /**
     * Displays a custom error message.
     *
     * @param message the error message to be displayed.
     */
    public void showError(String message) {
        output.append(message + "\n");
        ;
    }

    /**
     * Displays a horizontal line for visual separation.
     * <p>
     * The line is used to enhance readability in the user interface output.
     * </p>
     */
    public void showLine() {
        output.append("____________________________\n");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message the message to be displayed.
     */
    public void showMessage(String message) {
        output.append(message + "\n");
    }

    /**
     * Prompts the user for a search keyword and displays the results.
     */
    public void showSearchResults(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No tasks found with the given keyword.");
        } else {
            showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                showMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public String getOutput() {
        return output.toString();
    }

    public void clearOutput() {
        output.setLength(0);
    }
}
