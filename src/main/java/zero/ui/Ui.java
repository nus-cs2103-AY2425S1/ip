package zero.ui;

import zero.exception.ZeroException;
import zero.task.Task;
import zero.task.TaskList;

/**
 * The {@code Ui} class is responsible for interacting with the user.
 * It handles input and output operations, including displaying messages and reading user commands.
 */
public class Ui {
    private boolean isDone = false;

    /**
     * Constructs a {@code Ui} object and initialises the {@code Scanner} for reading user input.
     */
    public Ui() {
    }

    /**
     * Returns the welcome message to the user.
     *
     * @return The welcome message as a {@code String}.
     */
    public String showWelcome() {
        return  " Hello! I'm Zero\n"
                + " What can I do for you?\n";
    }

    /**
     * Returns the goodbye message to the user and sets the {@code isDone} flag to {@code true}.
     *
     * @return The goodbye message as a {@code String}.
     */
    public String showGoodbye() {
        this.isDone = true;
        return " Baibai!\n";
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to display.
     * @return The formatted error message as a {@code String}.
     */
    public String showError(String message) {
        return " oopsie, " + message + "\n";
    }

    /**
     * Returns a message indicating that a task has been added, along with the updated task count.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the task list.
     * @return The formatted message as a {@code String}.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return  " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Returns a message indicating that a task has been deleted, along with the updated task count.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the task list.
     * @return The formatted message as a {@code String}.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return  " Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The formatted message as a {@code String}.
     */
    public String showTaskMarked(Task task) {
        return  " Nice! I've marked this task as done:\n"
                + "   " + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     * @return The formatted message as a {@code String}.
     */
    public String showTaskUnmarked(Task task) {
        return  " OK, I've marked this task as not done yet:\n"
                + "   " + task + "\n";
    }

    /**
     * Returns the list of tasks to the user.
     *
     * @param tasks The task list containing the tasks to display.
     * @return The formatted list of tasks as a {@code String}.
     */
    public String listTasks(TaskList tasks) throws ZeroException {
        StringBuilder builder = new StringBuilder();
                builder.append(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            builder.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Returns the list of tasks matching a keyword to the user.
     *
     * @param tasks The task list containing the tasks to display.
     * @param keyword The keyword to match against task descriptions.
     * @return The formatted list of matching tasks as a {@code String}.
     */
    public String listMatchingTasks(TaskList tasks, String keyword) throws ZeroException {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        builder.append(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                builder.append(count).append(". ").append(tasks.getTask(i)).append("\n");
                count++;
            }
        }
        return builder.toString();
    }

    /**
     * Checks if the session is marked as done.
     *
     * @return {@code true} if the session is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
