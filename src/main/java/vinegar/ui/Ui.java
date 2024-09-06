package vinegar.ui;

import vinegar.task.TaskList;
import vinegar.task.Task;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 * <p>
 * The Ui class is responsible for displaying messages to the user, reading user input,
 * and managing interactions related to task management.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object to handle user interaction.
     * Initializes a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm vinegar.Vinegar\n" + "What Can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a divider line to separate sections in the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    /**
     * Displays an error message indicating failure to load tasks from a file.
     */
    public void showLoadingError() {
        System.out.println(" OOPS!!! Unable to load tasks from file.");
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The TaskList containing the current tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been successfully added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     */
    public void printTaskAdded(Task task, int size) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarked(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarked(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been successfully deleted.
     *
     * @param task The task that was deleted.
     * @param size The total number of tasks remaining in the list.
     */
    public void showDeleted(Task task, int size) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }
}
