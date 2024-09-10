package crack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user. It provides methods
 * for displaying messages, reading user input, and formatting output to be
 * presented to the user.
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________\n";
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println(DIVIDER
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + DIVIDER);
    }

    /**
     * Prints a divider line to visually separate sections of output.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("Goodbye!");
    }

    /**
     * Reads and returns the user's command input.
     *
     * @return the user's input as a trimmed string.
     */
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message in a formatted output.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println(DIVIDER + " Error: " + message + "\n" + DIVIDER);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display.
     */
    public void showMessage(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task the task that was added.
     * @param size the new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println(DIVIDER + " Got it. I've added this task:\n   " + task + "\n"
                + " Now you have " + size + " tasks in the list.\n" + DIVIDER);
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks the list of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(DIVIDER + " No tasks found with the given keyword.\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + " Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println(DIVIDER);
        }
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
