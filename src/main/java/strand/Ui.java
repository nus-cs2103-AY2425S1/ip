package strand;

import strand.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by displaying messages
 * and prompts.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "～～～～～～～～～～～～～～～～～～～～～～～～><>";
    private static final Integer INDENTATION = 4;

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param str The string to be printed.
     */
    private static String print(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println(" ".indent(INDENTATION));
        } else {
            System.out.println(str.indent(INDENTATION));
        }
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
     * @param err The error message to be displayed.
     */
    public String showError(String err) {
        return print(err);
    }

    /**
     * Displays a welcome message to the user.
     */
    public String welcome() {
        return print("ヾ(⌐■_■)ノ♪ Welcome! I'm Strand\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
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
     */
    public String addTask(Task task, Integer size) {
        return print(String.format("""
                (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
                  %s
                Now you have %d tasks in the list.""", task.toString(), size));
    }

    /**
     * Displays a message indicating that a task has been removed, along with
     * the current number of tasks in the list.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks in the list after removing the task.
     */
    public String deleteTask(Task task, Integer size) {
        return print(String.format("""
                (☞ﾟ∀ﾟ)☞ Task removed:
                %s
                Now you have %d tasks in the list.""", task.toString(), size));
    }

    /**
     * Displays a message indicating whether a task has been marked as done or not done.
     *
     * @param task The task that was marked.
     * @param mark {@code true} if the task was marked as done; {@code false} otherwise.
     */
    public String markTask(Task task, Boolean mark) {
        if (mark) {
            return print(String.format("( ﾟヮﾟ) You finished a task?! "
                    + "Congrats! I've marked this task as done:\n%s", task));
        } else {
            return print(String.format("ಠ_ಠ ...OK, I've marked this task as not done yet:\n"
                    + "%s", task.toString()));
        }
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String list(String tasks) {
        return print(tasks);
    }
}
