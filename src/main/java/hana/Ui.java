package hana;

import hana.task.Task;
import hana.task.TaskList;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final String line = "    ____________________________________________________________\n";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(line + "    Hello I'm Hana\n" + "    What can I do for you?\n" + line);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Reads the command input from the user.
     *
     * @return The command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message indicating that loading the task list failed.
     */
    public void showLoadingError() {
        System.out.println("    OOPS!!! Error loading the task list.");
    }

    /**
     * Displays the specified error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(line + "    " + message + "\n" + line);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(line + "    Got it. I've added this task:\n" +
                "      " + task + "\n" +
                "    Now you have " + taskCount + " tasks in the list.\n" + line);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(line + "    Noted. I've removed this task:\n" +
                "      " + task + "\n" +
                "    Now you have " + taskCount + " tasks in the list.\n" + line);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println(line + "    Nice! I've marked this task as done:\n" +
                "     " + task + "\n" + line);
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(line + "    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(line);
    }
}

