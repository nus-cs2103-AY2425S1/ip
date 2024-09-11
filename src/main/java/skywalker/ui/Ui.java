package skywalker.ui;

import java.util.Scanner;

import skywalker.task.Task;
import skywalker.task.TaskList;

/**
 * The Ui class handles interactions with the user.
 * It provides methods to display messages, errors, and the task list to the user,
 * as well as to read user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object that initializes a Scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next line of user input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line for separating sections of output.
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
        System.out.println("OOPS! " + message);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays an error message indicating that loading tasks from a file failed.
     */
    public void showLoadingError() {
        System.out.println("OOPS! Unable to load tasks from file.");
    }

    /**
     * Displays the goodbye message to the user when exiting the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }


    public void showDuplicateTaskMessage(Task task) {
        System.out.println("Duplicate task: " + task.getDescription() + " already exists!");
    }
}





