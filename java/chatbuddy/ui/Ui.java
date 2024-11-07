package chatbuddy.ui;

import chatbuddy.task.TaskList;
import chatbuddy.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input and displays messages.
 */
public class Ui {

    private final StringBuilder output;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.output = new StringBuilder();
    }

    /**
     * Returns the current output string to be displayed to the user.
     *
     * @return The output string.
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
        assert message != null && !message.trim().isEmpty() : "Error message must not be null or empty";
        output.append("OOPS!!! ").append(message).append("\n");
    }

    /**
     * Displays the task list to the user.
     *
     * @param tasks The list of tasks.
     */
    public void showTaskList(TaskList tasks) {
        assert tasks != null && tasks.size() > 0 : "Task list must not be null or empty";
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
        assert task != null : "Task must not be null";
        output.append("Got it. I've added this task:\n  ")
                .append(task).append("\n")
                .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param markedTasks The task(s) that were marked as done.
     * @param numberOfTasks The number of tasks marked as done.
     */
    public void showMarkTasks(String markedTasks, int numberOfTasks) {
        assert markedTasks != null && !markedTasks.trim().isEmpty() : "Marked tasks must not be null or empty";
        if (numberOfTasks == 1) {
            output.append("Nice! I've marked this task as done:\n ")
                    .append(markedTasks).append("\n");
        } else {
            output.append("Nice! I've marked these tasks as done:\n ")
                    .append(markedTasks).append("\n");
        }
    }

    /**
     * Displays a message when a task is unmarked (set as not done).
     *
     * @param unmarkedTasks The task(s) that were unmarked.
     * @param numberOfTasks The number of tasks unmarked.
     */
    public void showUnmarkTasks(String unmarkedTasks, int numberOfTasks) {
        assert unmarkedTasks != null && !unmarkedTasks.trim().isEmpty() : "Unmarked tasks must not be null or empty";
        if (numberOfTasks == 1) {
            output.append("OK, I've marked this task as not done yet:\n ")
                    .append(unmarkedTasks).append("\n");
        } else {
            output.append("OK, I've marked these tasks as not done yet:\n ")
                    .append(unmarkedTasks).append("\n");
        }
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param deletedTasks The task(s) that were deleted.
     * @param taskCount The current number of tasks in the list.
     * @param numberOfTasks The number of tasks deleted.
     */
    public void showDeleteTasks(String deletedTasks, int taskCount, int numberOfTasks) {
        assert deletedTasks != null && !deletedTasks.trim().isEmpty() : "Deleted tasks must not be null or empty";
        if (numberOfTasks == 1) {
            output.append("Noted. I've removed this task:\n")
                    .append(deletedTasks)
                    .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        } else {
            output.append("Noted. I've removed these tasks:\n")
                    .append(deletedTasks)
                    .append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        }
    }

    /**
     * Displays a list of tasks matching a search keyword.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showFindResult(ArrayList<Task> matchingTasks) {
        assert matchingTasks != null : "Matching tasks list must not be null";
        if (matchingTasks.isEmpty()) {
            output.append("No matching tasks found.\n");
        } else {
            output.append("Here are the matching tasks:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
    }

    /**
     * Displays a message when a task is updated.
     *
     * @param task The task that was updated.
     */
    public void showUpdateTask(Task task) {
        assert task != null : "Task must not be null";
        output.append("Got it. I've updated this task:\n ")
                .append(task).append("\n");
    }

    /**
     * Appends the goodbye message to the current output.
     * This method is typically called when the chatbot is about to exit or terminate the session.
     */
    public void showGoodbye() {
        output.append("Goodbye! Hope to see you again soon!");
    }
}
