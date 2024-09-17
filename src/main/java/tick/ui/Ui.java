package tick.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tick.storage.TaskList;
import tick.tasks.Task;

/**
 * Ui class handles the user interface of the application.
 */
public class Ui {
    private static final String separator = "____________________________________________________________";
    private final Scanner scanner;
    private String response;

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
        this.response = "System starting up...\n"
                + "Brrt brrt! I'm Tick!\n"
                + "What can I do for you?";
        System.out.println(Ui.separator);
        System.out.println(this.response);
        System.out.println(Ui.separator);
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        this.response = "System shutting down...\n"
                + "Bye bye, see you next time!";
        System.out.println(this.response);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        this.response = "An error occurred while loading data from file.";
        System.out.println(this.response);
    }

    /**
     * Shows a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        this.response = "Ding ding! I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        System.out.println(this.response);
    }

    /**
     * Shows a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        this.response = "Womp womp. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        System.out.println(this.response);
    }

    /**
     * Shows a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        this.response = "Ding ding! I've marked this task as done:\n"
                + task;
        System.out.println(this.response);
    }

    /**
     * Shows a message indicating that a task has been marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void showTaskUnmarked(Task task) {
        this.response = "OK, I've marked this task as undone:\n"
                + task;
        System.out.println(this.response);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.response = "Wow! You have no tasks in your list!";
            System.out.println(this.response);
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                sb.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
            }
            this.response = sb.toString();
            System.out.print(this.response);
        }
    }

    /**
     * Displays the tasks that match the search criteria.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            this.response = "Womp womp. No matching tasks found.";
            System.out.println(this.response);
        } else {
            StringBuilder sb = new StringBuilder("Ding ding! Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            this.response = sb.toString();
            System.out.print(this.response);
        }
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        this.response = "ERROR ERROR! " + message;
        System.out.println(this.response);
    }

    /**
     * Returns the response message.
     *
     * @return The response message.
     */
    public String getResponse() {
        return this.response;
    }
}
