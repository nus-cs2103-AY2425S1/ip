package lolo;

import lolo.task.Task;
import lolo.task.TaskList;

import java.util.Scanner;

/**
 * Handles the user interface for the Lolo application.
 * Provides methods to read user input and display messages to the user.
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
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm lolo.Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?\n");
    }

    /**
     * Displays a line separator to the user.
     */
    public void showLine() {
        System.out.println("______________________________________");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("lolo.Lolo: Bye. Hope to see you again soon! 👋");
    }

    /**
     * Displays an error message indicating a problem loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("    OOPS!!! " + message);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after the task was added.
     */
    public void showAddedTask(Task task, int size) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + size + " task(s) in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The total number of tasks in the list after the task was deleted.
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + size + " task(s) in the list.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTaskAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showMarkTaskAsNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays the list of tasks scheduled for a specific date.
     *
     * @param date The date for which tasks are being displayed.
     * @param tasks The TaskList containing the tasks on the specified date.
     */
    public void showTasksOnDate(String date, TaskList tasks) {
        System.out.println("    Here are the tasks on " + date + ":");
        for (Task task : tasks.getTasks()) {
            System.out.println("    " + task);
        }
    }

    /**
     * Displays tasks that match the keyword.
     *
     * @param keyword The keyword used for searching.
     * @param tasks The list of tasks that match the keyword.
     */
    public void showTasksByKeyword(String keyword, TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}

