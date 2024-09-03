package axel;

import java.util.Scanner;
import java.util.List;

/**
 * Handles all interactions with the user.
 * Provides methods to display messages and interact with the user through the command line.
 */
public class Ui {
    protected Scanner scanner;
    /**
     * Initializes the {@code Ui} object with a new {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("YO! YO! It's axel.Axel, at your service.");
        System.out.println("Hit me up with anything that I can help with!");
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Sad to see you go... goodbye!!");
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("HOLD YOUR HORSES!! " + message);
        System.out.println("____________________________________________________________");
    }
    /**
     * Reads a command entered by the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks in the list after removing the task.
     */
    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskNotDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }
}

