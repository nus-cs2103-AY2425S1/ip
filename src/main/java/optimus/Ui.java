package optimus;

import java.util.Scanner;

/**
 * Represents the user interface for the Optimus application.
 * The {@code Ui} class handles interactions with the user, including displaying messages
 * and reading input commands.
 */
public class Ui {
    private Scanner scanner;  // Scanner for reading user input.

    /**
     * Constructs a new {@code Ui} object that initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm optimus.Optimus");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command input from the user.
     *
     * @return the command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task the task that has been added.
     * @param size the current number of tasks in the list.
     */
    public void TaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task the task that has been removed.
     * @param size the current number of tasks in the list.
     */
    public void TaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Displays a list of tasks currently in the user's task list.
     *
     * @param tasks the list of tasks to display.
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.sizeOfRecord(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void TaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that has been marked as not done.
     */
    public void TaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display.
     */
    public void printError(String message) {
        System.out.println(message);
    }
}
