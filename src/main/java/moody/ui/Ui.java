package moody.ui;

import moody.tasks.Task;
import moody.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles the user interface for the Moody application.
 * The Ui class is responsible for displaying messages to the user and reading
 * user input. It provides methods to show task lists, task status updates, and errors.
 */
public class Ui {
    private static final String LINE = "___________________________________________________________";
    private static final String INDENT = "    ";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message to the user.
     * Prints a greeting and prompts the user for input.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Moody!\nWhat can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Returns a welcome message as a string.
     */
    public String showWelcomeAsString() {
        return LINE + "\nHello! I'm Moody!\nWhat can I do for you?\n" + LINE;
    }

    /**
     * Displays a goodbye message to the user.
     * Prints a farewell message when the application is exiting.
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Returns a goodbye message as a string.
     */
    public String showGoodbyeAsString() {
        return LINE + "\nBye. Hope to see you again soon!\n" + LINE;
    }

    /**
     * Displays the list of tasks.
     * Prints all tasks in the provided TaskList with their indices.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @return The formatted task list as a string.
     */
    public String showTaskListAsString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Displays a message indicating that a task has been added.
     * Prints the added task and the current number of tasks in the list.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been added as a string.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     * @return The formatted task added message as a string.
     */
    public String showTaskAddedAsString(Task task, int taskCount) {
        return LINE + "\nGot it. I've added this task:\n" + INDENT + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message indicating that a task has been removed.
     * Prints the removed task and the current number of tasks in the list.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(INDENT + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been removed as a string.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     * @return The formatted task removed message as a string.
     */
    public String showTaskRemovedAsString(Task task, int taskCount) {
        return LINE + "\nNoted. I've removed this task:\n" + INDENT + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * Prints the task that has been marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been marked as done as a string.
     *
     * @param task The task that has been marked as done.
     * @return The formatted marked task message as a string.
     */
    public String showMarkedTaskAsString(Task task) {
        return LINE + "\nNice! I've marked this task as done:\n" + task + "\n" + LINE;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     * Prints the task that has been marked as not completed.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been marked as not done as a string.
     *
     * @param task The task that has been marked as not done.
     * @return The formatted unmarked task message as a string.
     */
    public String showUnmarkedTaskAsString(Task task) {
        return LINE + "\nOK, I've marked this task as not done yet:\n" + task + "\n" + LINE;
    }

    /**
     * Displays a message indicating successful loading of tasks.
     * Prints a confirmation message when tasks are successfully loaded.
     */
    public void showLoadingSuccess() {
        System.out.println("Tasks loaded successfully!");
    }

    /**
     * Returns a message indicating successful loading of tasks as a string.
     *
     * @return The formatted loading success message as a string.
     */
    public String showLoadingSuccessAsString() {
        return "Tasks loaded successfully!";
    }

    /**
     * Displays an error message.
     * Prints the provided error message enclosed within a spacer.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Returns an error message as a string.
     *
     * @param message The error message to be returned.
     * @return The formatted error message as a string.
     */
    public String showErrorAsString(String message) {
        return LINE + "\n" + message + "\n" + LINE;
    }

    public void showMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Returns a general message as a string.
     *
     * @param message The message to be returned.
     * @return The formatted message as a string.
     */
    public String showMessageAsString(String message) {
        return LINE + "\n" + message + "\n" + LINE;
    }

    /**
     * Reads a command from the user input.
     * Reads a line of text input from the user and returns it as a trimmed string.
     *
     * @return The trimmed user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line to separate sections of text.
     * Prints a spacer line to create visual separation in the output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a horizontal line as a string.
     *
     * @return The horizontal line as a string.
     */
    public String showLineAsString() {
        return LINE;
    }
}
