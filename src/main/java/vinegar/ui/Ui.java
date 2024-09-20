package vinegar.ui;

import vinegar.task.TaskList;
import vinegar.task.Task;

import java.util.List;
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
    public String showWelcome() {
        return  "Hello! I'm vinegar.Vinegar\n" + "What Can I do for you?";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return " OOPS!!! " + message;
    }

    /**
     * Displays an error message indicating failure to load tasks from a file.
     */
    public String showLoadingError() {
        return " OOPS!!! Unable to load tasks from file.";
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The TaskList containing the current tasks.
     */
    public String showTaskList(TaskList tasks) {
        String taskString = "";
        taskString += " Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            taskString += i + 1 + "." + tasks.get(i) + "\n";
        }
        return taskString;
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public String showGoodbye() {
       return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that a task has been successfully added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     */
    public String printTaskAdded(Task task, int size) {
        return  " Got it. I've added this task:"
                + "   " + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarked(Task task) {
        return  " Nice! I've marked this task as done:"
                + "   " + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */

    public String showUnmarked(Task task) {
        return  " OK, I've marked this task as not done yet:"
                + "   " + task;
    }

    /**
     * Displays a message indicating that a task has been successfully deleted.
     *
     * @param task The task that was deleted.
     * @param size The total number of tasks remaining in the list.
     */
    public String showDeleted(Task task, int size) {
        return  " Noted. I've removed this task:"
                + "   " + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String showMatchingTaskList(List<Task> tasks) {
        String taskString = "";
        taskString += " Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskString += (i + 1) + "." + tasks.get(i) + "/n";
        }
        return taskString;
    }

    public String showMessage(String message) {
        return message;
    }
}
