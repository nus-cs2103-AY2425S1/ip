package Alex.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import Alex.Task.Task;

/**
 * Handles user interaction for the Alex chatbot.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a Ui object.
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
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        printDividerWithMessage("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Reads a command input by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a dividing line for formatting.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public void printTaskDeleted(Task task) {
        System.out.println("Task deleted: " + task);
    }

    /**
     * Displays a joke to the user.
     */
    public void printJoke() {
        printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
    }


    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }
    /**
     * Displays a message indicating a change in task status (e.g., marked as done or undone).
     *
     * @param message The message indicating the status change.
     * @param index   The index of the task whose status was changed.
     */
    public void printTaskStatusChange(String message, int index) {
        System.out.println(message + " " + (index + 1));
    }

    /**
     * Displays a message with a dividing line before and after it.
     *
     * @param message The message to be displayed.
     */
    private void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
}
