package hana;

import java.util.List;
import java.util.Scanner;

import hana.task.Task;
import hana.task.TaskList;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private final StringBuilder responseMessage;

    public Ui() {
        responseMessage = new StringBuilder();
    }

    public String getResponseMessage() {
        String message = responseMessage.toString();
        responseMessage.setLength(0); // Clear the message after returning
        return message;
    }

    /**
     * Displays the welcome message to the user.
     */
    public static String showWelcome() {
        return """
                Hello I'm Hana.\s
                What can I do for you?
                """;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        responseMessage.append("Bye. Hope to see you again soon!\n");
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
        responseMessage.append("OOPS!!! Error loading the task list.");
    }

    /**
     * Displays the specified error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        responseMessage.append(message);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        responseMessage.append("Got it. I've added this task:\n" + "      ")
                .append(task).append("\n").append("Now you have ")
                .append(taskCount)
                .append(" tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        responseMessage.append("Noted. I've removed this task:\n" + "      ")
                .append(task)
                .append("\n")
                .append("Now you have ")
                .append(taskCount)
                .append(" tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        responseMessage.append("Nice! I've marked this task as done:\n" + "     ").append(task);
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        responseMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            responseMessage.append("    ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
    }

    /**
     * Appends the search results to the response message, showing tasks that match a keyword.
     * If no tasks are found, a message indicating that no matches were found is returned.
     *
     * @param foundTasks The list of tasks that match the search keyword.
     */
    public void showFindResults(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            responseMessage.append("No matching tasks found.");
        } else {
            responseMessage.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                responseMessage.append("    ").append(i + 1).append(".").append(foundTasks.get(i)).append("\n");
            }
        }
    }
}

