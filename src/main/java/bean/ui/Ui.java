package bean.ui;

import java.util.List;
import java.util.Scanner;
import bean.task.Task;

/**
 * The Ui class handles user interactions, including displaying messages and reading user input.
 */
public class Ui {
//    private Scanner scanner;

    /**
     * Initializes a new Ui instance.
     */
//    public Ui() {
//        this.scanner = new Scanner(System.in);
//    }

    /**
     * Reads and returns user input as a trimmed string.
     *
     * @return The user's input.
     */
//    public String getUserInput() {
//        return scanner.nextLine().trim();
//    }

    /**
     * Returns a greeting message.
     *
     * @return The greeting message.
     */
    public String getGreeting() {
        return "Hello! I'm Bean\nWhat can I do for you?";
    }

    /**
     * Returns a goodbye message.
     *
     * @return The goodbye message.
     */
    public String getGoodbye() {
        return "Bye. Hope to see you again.";
    }

    /**
     * Returns the list of tasks formatted as a string.
     *
     * @param tasks The list of tasks.
     * @return The formatted string of tasks.
     */
    public String getTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            response.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return response.toString();
    }

    /**
     * Returns the list of matching tasks formatted as a string.
     *
     * @param tasks The list of matching tasks.
     * @return The formatted string of matching tasks.
     */
    public String getMatchingTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            response.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return response.toString();
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task       The task that was added.
     * @param numOfTasks The number of tasks after addition.
     * @return The formatted message.
     */
    public String getTaskAdded(Task task, int numOfTasks) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, numOfTasks);
    }

    /**
     * Returns a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     * @return The formatted message.
     */
    public String getTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been unmarked (set as not completed).
     *
     * @param task The task that was unmarked.
     * @return The formatted message.
     */
    public String getTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task       The task that was deleted.
     * @param numOfTasks The number of tasks remaining after deletion.
     * @return The formatted message.
     */
    public String getTaskDeleted(Task task, int numOfTasks) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", task, numOfTasks);
    }

    /**
     * Returns an error message.
     *
     * @param message The error message.
     * @return The formatted error message.
     */
    public String getError(String message) {
        return "Error: " + message;
    }

    /**
     * Returns an error message indicating that there was an issue loading tasks.
     *
     * @return The formatted error message.
     */
    public String getLoadingError() {
        return getError("Error loading tasks.");
    }
}