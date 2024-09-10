package strand;

import strand.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by displaying messages
 * and prompts.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "～～～～～～～～～～～～～～～～～～～～～～～～><>";
    private static final int INDENTATION = 4;

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param str The string to be printed.
     * @return The indented string.
     */
    private static String print(String str) {
        String message = (str == null || str.isEmpty()) ? " ".repeat(INDENTATION) : str;
        System.out.println(message.indent(INDENTATION));
        return str;
    }

    /**
     * Displays an error message indicating that the file could not be loaded.
     */
    public void showLoadingError() {
        print("Loading file…\n" + "█▒▒▒▒▒▒▒▒▒");
    }

    /**
     * Displays a horizontal line to separate sections of output.
     */
    public void showLine() {
        print(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     * @return The error message.
     */
    public String showError(String error) {
        return print(error);
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return The welcome message.
     */
    public String welcome() {
        return print("ヾ(⌐■_■)ノ♪ Welcome! I'm Strand\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return The goodbye message.
     */
    public String goodbye() {
        return print("Adios. Hope to see you again soon! ヾ(＾ ∇ ＾)");
    }

    /**
     * Displays a message indicating that a task has been added, along with
     * the current number of tasks in the list.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     * @return The message indicating the task has been added.
     */
    public String taskAdded(Task task, int size) {
        return print(String.format("""
                (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
                  %s
                Now you have %d tasks in the list.""", task, size));
    }

    /**
     * Displays a message indicating that a task has been removed, along with
     * the current number of tasks in the list.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks in the list after removing the task.
     * @return The message indicating the task has been removed.
     */
    public String taskRemoved(Task task, int size) {
        return print(String.format("""
                (☞ﾟ∀ﾟ)☞ Task removed:
                %s
                Now you have %d tasks in the list.""", task, size));
    }

    /**
     * Displays a message indicating whether a task has been marked as done or not done.
     *
     * @param task   The task that was marked.
     * @param marked {@code true} if the task was marked as done; {@code false} otherwise.
     * @return The message indicating the task's status.
     */
    public String taskMarked(Task task, boolean marked) {
        if (marked) {
            return print(String.format("( ﾟヮﾟ) You finished a task?! "
                    + "Congrats! I've marked this task as done:\n%s", task));
        } else {
            return print(String.format("ಠ_ಠ ...OK, I've marked this task as not done yet:\n"
                    + "%s", task));
        }
    }

    /**
     * Displays a message when a task has been assigned a priority.
     *
     * @param task     The task that was marked.
     * @param priority Priority that the task was assigned.
     * @return The message indicating the task's status.
     */
    public String priorityAssigned(Task task, Task.PriorityEnum priority) {
        return print(String.format(":> I've marked this task with priority %s"
                + ":\n%s", priority, task));
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The formatted list of tasks.
     */
    public String listTasks(String tasks) {
        return print(tasks);
    }
}
