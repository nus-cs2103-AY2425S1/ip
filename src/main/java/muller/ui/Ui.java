package muller.ui;

import java.util.Scanner;

import muller.command.MullerException;
import muller.task.TaskList;

/**
 * Handles all user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private Scanner sc;
    /**
     * Constructs a Ui object with a new Scanner for reading input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n" + logo);
    }
    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
    /**
     * Displays a line divider to separate sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
    /**
     * Displays a specified error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        try {
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ": " + tasks.get(i - 1));
                }
            }
        } catch (MullerException e) {
            showError(e.getMessage());
        }
    }
}
