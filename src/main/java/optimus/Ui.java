package optimus;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface for the Optimus application.
 * The {@code Ui} class handles interactions with the user, including displaying messages
 * and reading input commands.
 */
public class Ui {
    private Scanner scanner;  // Scanner for reading user input.

    /**
     * Constructs a new {@code Ui} object that initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm Optimus\nWhat can I do for you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads a command input from the user.
     *
     * @return the command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task the task that has been added.
     * @param size the current number of tasks in the list.
     */
    public String taskAdded(Task task, int size) {
        return "Got it. I've added this task:\n  " + task.toString() +
                "\nNow you have " + size + (size == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task the task that has been removed.
     * @param size the current number of tasks in the list.
     */
    public String TaskDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n  " + task.toString() +
                "\nNow you have " + size + (size == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Displays a list of tasks currently in the user's task list.
     *
     * @param tasks the list of tasks to display.
     */
    public String listTasks(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.sizeOfRecord(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public String TaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that has been marked as not done.
     */
    public String TaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Displays a list of tasks that match the search keyword.
     *
     * @param tasks the list of tasks to display.
     */
    public String listFoundTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display.
     */
    public String printError(String message) {
        return message;
    }

    /**
     * Returns a response message to display in the GUI.
     *
     * @param response the response message to display.
     * @return the response message as a string.
     */
    public String printResponse(String response) {
        return response;
    }
}
