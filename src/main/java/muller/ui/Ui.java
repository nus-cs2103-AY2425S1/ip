package muller.ui;

import java.util.List;
import java.util.Scanner;

import muller.command.MullerException;
import muller.task.Task;
import muller.task.TaskList;

/**
 * Handles all user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private Scanner scanner;
    /**
     * Constructs a Ui object with a new Scanner for reading input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
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
        return scanner.nextLine();
    }
    /**
     * Displays a line divider to separate sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays a confirmation of the task added.
     * @param task the task to add
     * @param tasks the list of tasks currently on the list
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
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
                for (int i = 1; i <= tasks.getSize(); i++) {
                    System.out.println(i + ": " + tasks.get(i - 1));
                }
            }
        } catch (MullerException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= matchingTasks.size(); i++) {
                System.out.println(i + ": " + matchingTasks.get(i - 1));
            }
        }
    }
}
