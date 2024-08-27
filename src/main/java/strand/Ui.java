package strand;

import strand.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by displaying messages
 * and prompts.
 */
public class Ui {
    private static final String horizontalLine = "～～～～～～～～～～～～～～～～～～～～～～～～><>";

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param str The string to be printed.
     */
    private static void print(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println(" ".indent(4));
        } else {
            System.out.println(str.indent(4));
        }
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
        print(horizontalLine);
    }

    /**
     * Displays an error message to the user.
     *
     * @param err The error message to be displayed.
     */
    public void showError(String err) {
        print(err);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        print("ヾ(⌐■_■)ノ♪ Welcome! I'm Strand\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void goodbye() {
        print("Adios. Hope to see you again soon! ヾ(＾ ∇ ＾)");
    }

    /**
     * Displays a message indicating that a task has been added, along with
     * the current number of tasks in the list.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     */
    public void addTask(Task task, Integer size) {
        print(String.format("""
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
    public void deleteTask(Task task, Integer size) {
        print(String.format("""
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
    public void markTask(Task task, Boolean mark) {
        if (mark) {
            print(String.format("( ﾟヮﾟ) You finished a task?! Congrats! I've marked this task as done:\n%s", task));
        } else {
            print(String.format("ಠ_ಠ ...OK, I've marked this task as not done yet:\n%s", task.toString()));
        }
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void list(TaskList tasks) {
        print(tasks.toString());
    }
}
