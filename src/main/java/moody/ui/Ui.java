package moody.ui;

import moody.tasks.Task;
import moody.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles the user interface for the Moody application.
 * The Ui class is responsible for displaying messages to the user and reading
 * user input. It provides methods to show task lists, task status updates, and errors.
 */
public class Ui {
    private static final String SPACER = "____________________________________________________________\n";
    private static final String INDENT = "    ";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message to the user.
     * Prints a greeting and prompts the user for input.
     */
    public void showWelcome() {
        System.out.println(SPACER + "Hello! I'm Moody!\nWhat can I do for you?\n" + SPACER);
    }

    /**
     * Displays a goodbye message to the user.
     * Prints a farewell message when the application is exiting.
     */
    public void showGoodbye() {
        System.out.println(SPACER + "Bye. Hope to see you again soon!\n" + SPACER);
    }

    /**
     * Displays the list of tasks.
     * Prints all tasks in the provided TaskList with their indices.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.print(SPACER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(SPACER);
    }

    /**
     * Displays a message indicating that a task has been added.
     * Prints the added task and the current number of tasks in the list.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(SPACER + "Got it. I've added this task:\n"
                + INDENT + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    /**
     * Displays a message indicating that a task has been removed.
     * Prints the removed task and the current number of tasks in the list.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println(SPACER + "Noted. I've removed this task:\n" + INDENT + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * Prints the task that has been marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     * Prints the task that has been marked as not completed.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating successful loading of tasks.
     * Prints a confirmation message when tasks are successfully loaded.
     */
    public void showLoadingSuccess() {
        System.out.println("Tasks loaded successfully!");
    }

    /**
     * Displays an error message.
     * Prints the provided error message enclosed within a spacer.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(SPACER + message + SPACER);
    }

    /**
     * Reads a command from the user input.
     * Reads a line of text input from the user and returns it as a trimmed string.
     *
     * @return The trimmed user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line to separate sections of text.
     * Prints a spacer line to create visual separation in the output.
     */
    public void showLine() {
        System.out.println(SPACER);
    }
}
