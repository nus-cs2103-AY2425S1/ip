package beechat.util;

import beechat.task.TaskList;

import java.util.Scanner;

/**
 * Handles the user interface.
 * Displays messages to the user and reads user input.
 */
public class Ui {

    /** Scanner to read user input from the command line. */
    private final Scanner sc;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
    }

    /**
     * Reads the user's input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays an error message if there was an error loading the task list.
     */
    public void showLoadingError() {
        System.out.println("There was an error loading your task list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the bye message when the user is leaving the application.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all tasks in the task list to the user.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks so far");
        } else {
            System.out.println("Here are all the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks.getTask(i)));
            }
        }
    }
}
