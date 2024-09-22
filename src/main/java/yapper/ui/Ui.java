package yapper.ui;

import java.util.List;
import java.util.Scanner;

import yapper.task.Task;
import yapper.task.TaskList;

/**
 * Represents the user interface that interacts with the user by printing messages
 * and taking input commands.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} object with an input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you Boss?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a divider line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user's input command.
     *
     * @return The input command as a {@code String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints an error message to the user.
     *
     * @param message The error message to display.
     */
    public void printError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that an error occurred while loading tasks from the file.
     */
    public void printLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println(" Error loading tasks from file!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message when the user exits the program.
     */
    public void printGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye Boss!. Yapper wants to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks currently in the list.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it Boss! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that a task has been removed from the list.
     *
     * @param task The task that was removed.
     * @param taskCount The number of tasks currently in the list.
     */
    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Okay bOSS ur task got removed");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param tasks The list of tasks to print.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list Boss!:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice Boss! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK Boss! I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks that match the user's search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void showMatchingTasks(List<Task> tasks) {
        System.out.println("Boss, here is the tasks you requested!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints a message indicating that no matching tasks were found.
     */
    public void showNoMatchingTasksMessage() {
        System.out.println("No matching tasks have been found, Boss!");
    }
}
