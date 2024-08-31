package chobo;

import java.util.ArrayList;

/**
 * The type Ui.
 */
public class Ui {
    private static final String LINE = "----------------------------------------";

    /**
     * Print welcome.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Print goodbye.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Print line.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Print error.
     *
     * @param message the message
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Print task added.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public void printTaskAdded(Task task, int totalTasks) {
        printLine();
        System.out.println("added: " + task);
        System.out.println(totalTasks + " task(s) in the list");
        printLine();
    }

    /**
     * Print task deleted.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public void printTaskDeleted(Task task, int totalTasks) {
        printLine();
        System.out.println("deleted: " + task);
        System.out.println(totalTasks + " task(s) left in the list");
        printLine();
    }

    /**
     * Print task list.
     *
     * @param tasks the tasks
     */
    public void printTaskList(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    /**
     * Show task marked.
     *
     * @param task the task
     */
    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Nice! I have marked this task as done:\n" + task);
        printLine();
    }

    /**
     * Show task unmarked.
     *
     * @param task the task
     */
    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("OK, I have marked this task as not done yet\n" + task);
        printLine();
    }
}


