package spike.ui;

import java.util.ArrayList;
import java.util.Scanner;

import spike.tasks.Task;

/**
 * Represents the User Interface of the application.
 */
public class Ui {
    private static final String LINE = "    _________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     * Initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println(Ui.LINE);
    }

    /**
     * Shows the error message when loading from file fails.
     */
    public void showLoadingError() {
        System.out.println("     An error occurred while reading from file!\nStarting with a new task list.");
    }

    /**
     * Shows the welcome message.
     */
    public void showHello() {
        showLine();
        System.out.println("     Hello! I'm Spike\n     What can I do for you?");
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Reads the command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Shows the task list to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("     Your list is empty!");
        } else {
            System.out.println("     Here are the tasks in your list:\n");
            int counter = 0;
            for (Task item : tasks) {
                counter++;
                System.out.println("      " + counter + ". " + item.toString());
            }
        }
    }

    /**
     * Shows the task that was added to the list.
     *
     * @param task The task that was added.
     * @param size The size of the list after adding the task.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("     Got it. I've added this task:\n");
        System.out.println("      " + task.toString());
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the task that was deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The size of the list after deleting the task.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("     Noted. I've removed this task:\n");
        System.out.println("      " + task.toString());
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the task that was marked.
     *
     * @param taskString The string representation of the task that was marked.
     */
    public void showTaskMarked(String taskString) {
        System.out.println("     Nice! I've marked this task as done:\n");
        System.out.println("      " + taskString);
    }

    /**
     * Shows the task that was unmarked.
     *
     * @param taskString The string representation of the task that was unmarked.
     */
    public void showTaskUndone(String taskString) {
        System.out.println("     I've marked this task as not done yet:\n");
        System.out.println("      " + taskString);
    }

    /**
     * Shows the error message when an error command is executed.
     *
     * @param message The error message to be displayed.
     */
    public void showExceptionMessage(String message) {
        System.out.println("     " + "ERROR: " + message);
    }
}
