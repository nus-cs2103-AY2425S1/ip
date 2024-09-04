package shoai;

import shoai.TaskList; // Imports TaskList for managing and displaying tasks
import shoai.Task; // Imports Task for displaying task information

import java.util.ArrayList;
import java.util.Scanner; // Imports Scanner for reading user input

/**
 * Handles user interface interactions, including reading user commands and displaying
 * various messages and task lists to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object that initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message to the user.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm ShoAI\nWhat can I do for you?\n" + getLine();
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The line of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a line of underscores to separate different sections in the output.
     *
     * @return The line of underscores.
     */
    private String getLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns an error message when there is an issue loading tasks from the file.
     *
     * @return The error message.
     */
    public String showLoadingError() {
        return "Error loading tasks from file. Starting with an empty task list.";
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to display.
     * @return The error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a message indicating that the application is exiting.
     *
     * @return The exit message.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the current list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     * @return The list of tasks as a string.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     * @return The task added message.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + task +
                "\nNow you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.";
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     * @return The task deleted message.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Noted. I've removed this task:\n  " + task +
                "\nNow you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The task marked message.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done.
     * @return The task unmarked message.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns the matching tasks found based on the keyword search.
     *
     * @param tasks The TaskList containing the tasks to search through.
     * @param keyword The keyword to search for in the task descriptions.
     * @return The search results as a string.
     */
    public String showFindResults(TaskList tasks, String keyword) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        StringBuilder sb = new StringBuilder();
        sb.append(getLine()).append("\n");
        if (matchingTasks.isEmpty()) {
            sb.append("No tasks found matching the keyword: ").append(keyword);
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1) + "." + matchingTasks.get(i) + "\n");
            }
        }
        sb.append(getLine());
        return sb.toString();
    }
}
