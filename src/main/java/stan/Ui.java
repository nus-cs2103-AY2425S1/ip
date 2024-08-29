package stan;

import stan.tasks.Task;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user.
 * It provides methods to read user input, display output messages, and show errors.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     * Initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The full command string.
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
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Stan");
        System.out.println(" What can I do for you today?");
        showLine();
    }

    /**
     * Displays the goodbye message when the chatbot exits.
     */
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
        showLine();
    }
}
