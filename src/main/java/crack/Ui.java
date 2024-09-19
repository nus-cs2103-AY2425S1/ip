package crack;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Ui class handles all interactions with the user. It provides methods
 * for displaying messages, reading user input, and formatting output to be
 * presented to the user.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }





    /**
     * Displays the goodbye message when the user exits the application.
     */
    public String showGoodbye() {
        return "Goodbye!\n";
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
    public String showError(String message) {
        return (" Error: " + message + "\n");
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to display.
     */
    public void showMessage(String message) {
        assert message != null : "Message to display cannot be null";
        System.out.println(message + "\n");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task the task that was added.
     * @param size the new size of the task list.
     */
    public String showTaskAdded(Task task, int size) {
        return (" Got it. I've added this task:\n   " + task + "\n"
                + " Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks the list of matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return (" No tasks found with the given keyword.\n");
        } else {
            String output = (" Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output += ((i + 1) + "." + tasks.get(i) + "\n");
            }
            return output;
        }
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
