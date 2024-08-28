package tick.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tick.tasks.Task;
import tick.storage.TaskList;

/**
 * Ui class handles the user interface of the application.
 */
public class Ui {
    private static final String separator = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println(Ui.separator);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println(Ui.separator);
        System.out.println("System starting up...");
        System.out.println("Brrt brrt! I'm Tick!");
        System.out.println("What can I do for you?");
        System.out.println(Ui.separator);
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("System shutting down...");
        System.out.println("Bye bye, see you next time!");
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading data from file.");
    }

    /**
     * Shows a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Ding ding! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Shows a message indicating that a task has been marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as undone:");
        System.out.println(task);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.printf("%d.%s\n", i + 1, tasks.getTask(i));
            }
        }
    }

    /**
     * Displays the tasks that match the search criteria.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, tasks.get(i));
            }
        }
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("ERROR ERROR! " + message);
    }
}
