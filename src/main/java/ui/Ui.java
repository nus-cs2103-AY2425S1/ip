package ui;

import storage.TaskList;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner scanner;
    private static final String LINE = "____________________________________________________________";

    /**
     * Creates a user interface.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    private void printLine() {
        System.out.println(LINE);
    }

    /**
     * Shows the greeting message.
     */
    public void showGreeting() {
        printLine();
        System.out.println("Hello! I'm Dude!\nWhat can I do for you?");
        printLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks to be shown.
     */
    public void showList(TaskList tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i));
            }
        }
        printLine();
    }

    /**
     * Shows the task that was deleted.
     *
     * @param task The task that was deleted.
     * @param tasks The list of tasks.
     */
    public void showDeleted(Task task, TaskList tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    /**
     * Shows the task that was added.
     *
     * @param task The task that was added.
     * @param tasks The list of tasks.
     */
    public void showAdded(Task task, TaskList tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    /**
     * Shows the task that was marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarked(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    /**
     * Shows the task that was marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarked(Task task) {
        printLine();
        System.out.println(("OK, I've marked this task as not done yet:"));
        System.out.println(task);
        printLine();
    }

    /**
     * Shows an error message.
     *
     * @param e The error message.
     */
    public void showError(String e) {
        printLine();
        System.out.println("Error! " + e);
        printLine();
    }

    /**
     * Shows the tasks in a filtered list.
     *
     * @param tasks The list of tasks that match the keyword.
     */
    public void showMatching(ArrayList<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
        printLine();
    }

    /**
     * Shows an error message when loading data.
     */
    public void showLoadingError() {
        printLine();
        System.out.println("Error! An error occurred while loading data from your file!");
        printLine();
    }
}
