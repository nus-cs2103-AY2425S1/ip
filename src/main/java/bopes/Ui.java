package bopes;

import java.util.Scanner;
import bopes.task.Task;
import bopes.task.TaskList;

/**
 * The Ui class handles all interactions with the user. It provides methods to display messages,
 * read user input, and show the status of tasks in the task list.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Bopes is a personal assistant that helps you manage your tasks.");
    }

    /**
     * Reads and returns the user's command input.
     * If no input is detected, the program exits.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        System.out.print("What can I do for you? ");
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            System.out.println("No input detected. Exiting...");
            System.exit(0);
            return "";
        }
    }

    /**
     * Displays a dividing line to separate different sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________\n");
    }

    /**
     * Displays an error message indicating that there was a problem loading tasks from storage.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from storage.");
    }

    /**
     * Displays a custom error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks currently in the task list.
     * If the task list is empty, it notifies the user that no tasks were found.
     *
     * @param tasks the TaskList object containing the tasks to be displayed
     */
    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found in storage.");
        } else {
            System.out.println("Here are your tasks:");
            tasks.printTasks();
        }
    }

    /**
     * Displays a message indicating that a new task has been added to the task list.
     *
     * @param task the Task that was added
     * @param size the current number of tasks in the task list
     */
    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the Task that was marked as done
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the Task that was marked as not done
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task the Task that was deleted
     * @param size the current number of tasks remaining in the task list
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param matchingTasks the TaskList containing tasks that match the search keyword
     */
    public void showFoundTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks match your search.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.getTasks().get(i));
            }
        }
    }

    public void showGoodbye() {
        System.out.println("Goodbye!");
    }
}
