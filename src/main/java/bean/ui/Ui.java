package bean.ui;

import bean.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns user input as a trimmed string.
     *
     * @return The user's input.
     */
    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println("______________________________");
        System.out.println("Hello! I'm bean.Bean");
        System.out.println("What can I do for you?");
        System.out.println("______________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("______________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("______________________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        System.out.println("______________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    /**
     * Displays the list of matching tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showMatchingTasks(List<Task> tasks) {
        System.out.println("______________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    public void showTaskAdded(Task task, int numOfTasks) {
        System.out.println("______________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public void showTaskMarked(Task task) {
        System.out.println("______________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("______________________________");
    }

    /**
     * Displays a message indicating that a task has been unmarked (set as not completed).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("______________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("______________________________");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param numOfTasks The number of tasks remaining after deletion.
     */
    public void showTaskDeleted(Task task, int numOfTasks) {
        System.out.println("______________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("______________________________");
        System.out.println(message);
        System.out.println("______________________________");
    }

    /**
     * Displays an error message indicating that there was an issue loading tasks.
     */
    public void showLoadingError() {
        showError("Error loading tasks.");
    }
}
