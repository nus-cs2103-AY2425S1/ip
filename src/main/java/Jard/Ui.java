package Jard;

import java.util.List;

/**
 * Handles user interactions and displays messages to the user.
 */
public class Ui {
    private StringBuilder output;

    /**
     * Construct a Ui instance.
     */
    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Displays a welcome message with the Jard logo and a greeting.
     */
    public String showWelcome() {
        output.setLength(0);  // Clear previous output
        output.append("Hello from Jardaloon\n")
                .append("Hello! I'm Jard.\n")
                .append("What can I do for you?");
        return output.toString();
    }

    /**
     * Displays a goodbye message when the application is closed.
     */
    public String showBye() {
        output.setLength(0);  // Clear previous output
        output.append("Bye. Hope to see you again soon!");
        return output.toString();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showTaskList(List<Task> tasks) {
        output.setLength(0);  // Clear previous output
        if (tasks.isEmpty()) {
            output.append("Nothing in the list!");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Displays a message indicating that a new task has been added.
     *
     * @param task The added task.
     * @param size The current number of tasks in the list.
     */
    public String showAddTask(Task task, int size) {
        output.setLength(0);  // Clear previous output
        output.append("Got it. I've added this task:\n")
                .append("   ").append(task).append("\n")
                .append("Now you have ").append(size).append(" tasks in the list.");
        return output.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTask(Task task) {
        output.setLength(0);  // Clear previous output
        output.append("Nice! I've marked this task as done:\n")
                .append("   ").append(task);
        return output.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmarkTask(Task task) {
        output.setLength(0);  // Clear previous output
        output.append("OK, I've marked this task as not done yet:\n")
                .append("   ").append(task);
        return output.toString();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public String showDeleteTask(Task task, int size) {
        output.setLength(0);  // Clear previous output
        output.append("Noted. I've removed this task:\n")
                .append("   ").append(task).append("\n")
                .append("Now you have ").append(size).append(" tasks in the list.");
        return output.toString();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        output.setLength(0);  // Clear previous output
        output.append("Jard! ").append(message);
        return output.toString();
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public String showFindResults(List<Task> tasks) {
        output.setLength(0);  // Clear previous output
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString();
    }
}
