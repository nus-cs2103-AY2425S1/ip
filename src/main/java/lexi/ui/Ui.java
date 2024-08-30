package lexi.ui;

import lexi.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * This class is responsible for displaying messages to the user and reading input from the user.
 */
public class Ui {
    private final static String LINE_BREAK = "____________________________________________________________\n";

    /**
     * Displays a line break for better readability of the output.
     */
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println(" Hello! I'm Lexi\n What can I do for you?");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command input by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskSize The total number of tasks in the list after adding this task.
     */
    public void showAddMessage(Task task, int taskSize) {
        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", taskSize, taskSize == 1 ? "" : "s");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskSize The total number of tasks remaining in the list after deleting this task.
     */
    public void showDeleteMessage(Task task, int taskSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskSize);
    }

    /**
     * Displays an error message if there was an issue loading data.
     */
    public void showLoadingError() {
        System.out.println("Something went wrong with loading data!\n");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param taskToBeMarked The task that was marked as done.
     */
    public void showMarkMessage(Task taskToBeMarked) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);
    }

    /**
     * Displays a message confirming that a task has been unmarked (marked as not done).
     *
     * @param taskToBeMarked The task that was unmarked.
     */
    public void showUnmarkMessage(Task taskToBeMarked) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + taskToBeMarked);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showListOfTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                System.out.printf("  %d. %s%n", i + 1, currTask);
            }
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
