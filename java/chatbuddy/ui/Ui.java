package chatbuddy.ui;

import chatbuddy.task.TaskList;
import chatbuddy.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input and displays messages.
 */
public class Ui {

    private StringBuilder output;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.output = new StringBuilder();
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input command.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Clears the output displayed on the UI.
     */
    public void clearOutput() {
        output.setLength(0);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        output.append("Hello! I'm ChatBuddy.\nLet me know how I can assist you today.\n");
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        output.append("_____________________________________________\n");
    }

    /**
     * Displays an error message when the tasks fail to load.
     */
    public void showLoadingError() {
        output.append("An error occurred while loading the tasks.\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        output.append("OOPS!!! ").append(message).append("\n");
    }

    /**
     * Displays the task list to the user.
     *
     * @param tasks The list of tasks.
     */
    public void showTaskList(TaskList tasks) {
        output.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
        }
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showAddTask(Task task, int taskCount) {
        output.append("Got it. I've added this task:\n  ")
                .append(task).append("\n")
                .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        output.append("Nice! I've marked this task as done:\n  ")
                .append(task).append("\n");
    }

    /**
     * Displays a message when a task is unmarked (set as not done).
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkTask(Task task) {
        output.append("OK, I've marked this task as not done yet:\n  ")
                .append(task).append("\n");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showDeleteTask(Task task, int taskCount) {
        output.append("Noted. I've removed this task:\n  ")
                .append(task).append("\n")
                .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    /**
     * Displays the tasks that match the search keyword entered by the user.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public void showFindResult(ArrayList<Task> matchingTasks) {
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
        }
    }
}
