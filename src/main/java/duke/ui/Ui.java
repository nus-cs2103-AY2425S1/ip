package duke.ui;

import java.util.List;

import duke.tasks.Task;

/**
 * Represents the User Interface (UI) for the DailyTasks application.
 * This class is responsible for displaying messages to the user
 * and formatting task-related information.
 */
public class Ui {

    /** The name of the bot. */
    public static final String BOT_NAME = "DailyTasks";

    /** Greeting message displayed to the user upon startup. */
    public static final String GREETING = "Hello! I'm " + BOT_NAME + ", your awesome task planner!";

    /**
     * Formats a single task with its corresponding index for display.
     *
     * @param i The index of the task.
     * @param task The task to be formatted.
     * @return The formatted task as a string.
     */
    private static String formatSingleTask(int i, Task task) {
        return i + ". " + task.toString() + "\n";
    }

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return Ui.formatOutputMessage(GREETING);
    }

    /**
     * Formats a list of tasks for display.
     *
     * @param tasks The list of tasks to be displayed.
     * @param filtered Whether the task list is filtered.
     * @return The formatted task list as a string.
     */
    public String formatTaskListings(List<Task> tasks, boolean filtered) {
        StringBuilder str = new StringBuilder();

        str.append(filtered ? "Here are the filtered tasks:" : "Here are the tasks in your list:")
                .append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(Ui.formatSingleTask(i + 1, tasks.get(i)));
        }

        return str.toString();
    }

    /**
     * Formats an output message to be displayed within borders.
     *
     * @param input The message to be displayed.
     * @return The formatted message as a string.
     */
    public static String formatOutputMessage(String input) {
        return input;
    }

    /**
     * Formats a message when a task is added to the list.
     *
     * @param taskCount The number of tasks in the list.
     * @param task The task that was added.
     * @return The formatted message as a string.
     */
    public static String formatAddTask(int taskCount, Task task) {
        return (
                "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                        + "Now you have " + taskCount + " tasks in the list." + "\n"
            );
    }

    /**
     * Formats a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return The formatted message as a string.
     */
    public String formatMarkTask(Task task) {
        return (
                "Nice! I've marked this task as done:" + "\n"
                        + task.toString() + "\n"
            );
    }

    /**
     * Formats a message when a task is unmarked as done.
     *
     * @param task The task that was unmarked.
     * @return The formatted message as a string.
     */
    public String formatUnmarkTask(Task task) {
        return (
                "OK, I've marked this task as not done yet:" + "\n"
                        + task.toString() + "\n"
            );
    }

    /**
     * Formats a message when a task is deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The remaining number of tasks in the list.
     * @return The formatted message as a string.
     */
    public String formatDeleteTask(Task task, int size) {
        return (
                "Noted. I've removed this task:" + "\n"
                        + task.toString() + "\n"
                        + "Now you have " + size + " tasks in the list." + "\n"
            );
    }

    /**
     * Formats a message when a priority is added to a task.
     *
     * @param task The task that was deleted.
     * @return The formatted message as a string.
     */
    public String formatAddPriorityTask(Task task) {
        return (
                "Noted. I've added the priority " + task.getPriority() + " to this task:" + "\n"
                        + task.toString() + "\n"
            );
    }
}
