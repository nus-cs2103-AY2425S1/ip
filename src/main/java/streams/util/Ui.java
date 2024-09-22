package streams.util;

import java.util.List;
import java.util.Scanner;

import streams.task.Task;

/**
 * Handles user interface operations.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs an Ui object and initializes the scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a welcome message.
     */
    public String showWelcome() {
        return "Hello from Streams!\nWhat can I do for you?";
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's command as a String.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays a list of tasks that match a keyword.
     *
     * @param tasks The list of matching tasks to display.
     */
    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("oops!!! no existing task file has been found :( starting with an empty task list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
