package Alex.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import Alex.Task.Task;

/**
 * Handles user interface operations for displaying messages and reading input.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private StringBuilder output = new StringBuilder();

    /**
     * Constructs a Ui object to handle user interactions.
     */
    public Ui() {
        // No need to pass the task list
    }
    /**
     * Returns the stored output and clears the StringBuilder for the next response.
     *
     * @return The response message.
     */
    public String getOutput() {
        String result = output.toString();
        output.setLength(0); // Clear the StringBuilder for the next command
        return result;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        appendMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");
    }

    public void showBye() {
        appendMessage(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     * @return The error message.
     */
    public String showError(String message) {
        appendMessage("Error: " + message);
        return message; // Return the error message
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        appendMessage("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Reads a command input by the user.
     *
     * @return The command entered by the user.
     */

    /**
     * Prints a joke to the user.
     */
    public String showJoke() {
        return "Why do programmers prefer dark mode? Because the light attracts bugs!";
    }


    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            appendMessage("No matching tasks found.");
        } else {
            appendMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                appendMessage((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays a message indicating a change in task status (e.g., marked as done or undone).
     *
     * @param message The message indicating the status change.
     * @param index   The index of the task whose status was changed.
     */
    public void showTaskStatusChange(String message, int index) {
        appendMessage(message + " for task " + (index + 1));
    }

    /**
     * Prints a divider with a message.
     *
     * @param message The message to be displayed between dividers.
     */
    private void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
    /**
     * Appends a message to the output.
     *
     * @param message The message to be added to the output.
     */
    public void appendMessage(String message) {
        output.append(message).append("\n");
    }
}

