package Alex.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import Alex.Task.Task;

/**
 * Handles user interface operations for displaying messages and reading input.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Ui object to handle user interactions.
     */
    public Ui() {
        // No need to pass the task list
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        printDividerWithMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printDividerWithMessage(message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded.
     */
    public void showLoadingError() {
        printDividerWithMessage("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Reads and returns the user's command input.
     *
     * @return The command input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a divider line in the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public void printTaskDeleted(Task task) {
        System.out.println("Task deleted: " + task);
    }

    /**
     * Prints a joke to the user.
     */
    public void printJoke() {
        printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints a status change message for a task.
     *
     * @param message The status change message.
     * @param index The index of the task in the list.
     */
    public void printTaskStatusChange(String message, int index) {
        System.out.println(message + " " + (index + 1));
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
}

