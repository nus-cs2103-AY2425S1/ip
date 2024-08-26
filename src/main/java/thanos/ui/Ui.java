package thanos.ui;

import java.util.ArrayList;

import thanos.tasks.Task;

/**
 * Handles the user interface interactions for displaying messages and tasks.
 * <p>
 * The {@code Ui} class provides methods to output various types of messages and task information
 * to the console. It includes methods for displaying welcome messages, task lists, and notifications
 * about task additions, removals, and status changes.
 * </p>
 */
public class Ui {
    /**
     * Displays a string message to the console and adds a divider.
     *
     * @param s the string message to display.
     */
    public void display(String s) {
        System.out.print(s);
        printDivider();
    }

    /**
     * Prints a welcome message to the user.
     */
    public void displayWelcome() {
        this.display("Hello! I'm Thanos!\nWhat can I do for you?\n");
    }

    /**
     * Prints the specified header followed by the list of tasks. Each task is preceded by its index in the list.
     * If the task list is empty, a message indicating that no tasks are found is displayed.
     *
     * @param taskList the list of tasks to display.
     * @param header the header to display before the task list.
     */
    public void displayTasks(ArrayList<Task> taskList, String header) {
        if (taskList.isEmpty()) {
            this.display("No tasks found\n");
            return;
        }

        System.out.println(header);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        this.display(sb.toString());
    }

    /**
     * Prints a message showing the task that has been added and the updated number of tasks in the list.
     *
     * @param task the task that has been added.
     * @param size the updated size of the task list.
     */
    public void displayTaskAdded(Task task, int size) {
        this.display(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        ));
    }

    /**
     * Prints a message showing the task that has been removed and the updated number of tasks in the list.
     *
     * @param task the task that has been removed.
     * @param size the updated size of the task list.
     */
    public void displayTaskRemoved(Task task, int size) {
        this.display(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        ));
    }

    /**
     * Prints a confirmation message showing the task that has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void displayTaskMarked(Task task) {
        this.display(String.format("Nice! I've marked this task as done:\n  %s\n", task));
    }

    /**
     * Prints a confirmation message showing the task that has been marked as not done yet.
     *
     * @param task the task that has been marked as not done.
     */
    public void displayTaskUnmarked(Task task) {
        this.display(String.format("OK, I've marked this task as not done yet:\n  %s\n", task));
    }

    /**
     * Prints a horizontal divider line.
     */
    public static void printDivider() {
        System.out.println("-".repeat(50));
    }
}
