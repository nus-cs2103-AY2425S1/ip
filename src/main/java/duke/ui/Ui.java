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

    /** Goodbye message displayed when the user exits the application. */
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    /** Indentation spacing for formatted output. */
    public static final String SPACING = "    ";

    /** Border used for formatting messages. */
    public static final String BORDER = "____________________________________________________________";

    /**
     * Returns the formatted border with proper indentation.
     *
     * @return The formatted border as a string.
     */
    public static String formattedBorder() {
        return Ui.SPACING + Ui.BORDER;
    }

    /**
     * Formats a single task with its corresponding index for display.
     *
     * @param i The index of the task.
     * @param task The task to be formatted.
     * @return The formatted task as a string.
     */
    private static String formatSingleTask(int i, Task task) {
        return Ui.SPACING + " " + i + "." + task.toString() + "\n";
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(Ui.formatOutputMessage(GREETING));
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println(Ui.formatOutputMessage(GOODBYE));
    }

    public void showMessage(String message) {
        System.out.println(message);
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

        str.append(Ui.formattedBorder()).append("\n");
        str.append(Ui.SPACING + " ")
                .append(filtered ? "Here are the filtered tasks:" : "Here are the tasks in your list:")
                .append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(Ui.formatSingleTask(i + 1, tasks.get(i)));
        }
        str.append(Ui.formattedBorder()).append("\n");

        return str.toString();
    }

    /**
     * Formats an output message to be displayed within borders.
     *
     * @param input The message to be displayed.
     * @return The formatted message as a string.
     */
    public static String formatOutputMessage(String input) {
        return (
                Ui.formattedBorder() + "\n"
                        + Ui.SPACING + " " + input + "\n"
                        + Ui.formattedBorder() + "\n"
            );
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
                Ui.formattedBorder() + "\n"
                        + Ui.SPACING + " " + "Got it. I've added this task:" + "\n"
                        + Ui.SPACING + "   " + task.toString() + "\n"
                        + Ui.SPACING + " " + "Now you have " + taskCount + " tasks in the list." + "\n"
                        + Ui.formattedBorder() + "\n"
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
                Ui.formattedBorder() + "\n"
                        + Ui.SPACING + " " + "Nice! I've marked this task as done:" + "\n"
                        + Ui.SPACING + "   " + task.toString() + "\n"
                        + Ui.formattedBorder() + "\n"
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
                Ui.formattedBorder() + "\n"
                        + Ui.SPACING + " " + "OK, I've marked this task as not done yet:" + "\n"
                        + Ui.SPACING + "   " + task.toString() + "\n"
                        + Ui.formattedBorder() + "\n"
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
                Ui.formattedBorder() + "\n"
                        + Ui.SPACING + " " + "Noted. I've removed this task:" + "\n"
                        + Ui.SPACING + "   " + task.toString() + "\n"
                        + Ui.SPACING + " " + "Now you have " + size + " tasks in the list." + "\n"
                        + Ui.formattedBorder() + "\n"
            );
    }
}
