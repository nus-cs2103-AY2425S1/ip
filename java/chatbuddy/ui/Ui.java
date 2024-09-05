package chatbuddy.ui;

import chatbuddy.task.TaskList;
import chatbuddy.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input and displays messages.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm ChatBuddy.\nLet me know how can I assist you today?");
        System.out.println("_____________________________________________");
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("_____________________________________________");
    }

    /**
     * Displays an error message when the tasks fail to load.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading the tasks.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays the task list to the user.
     *
     * @param tasks The list of tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showAddTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message when a task is unmarked (set as not done).
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showDeleteTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the tasks that match the search keyword entered by the user.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public void showFindResult(ArrayList<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }
}
