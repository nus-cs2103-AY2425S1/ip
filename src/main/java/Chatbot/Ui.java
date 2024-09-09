package Chatbot;

import Tasks.Task;
import java.util.Map;

/**
 * The {@code Ui} class handles the user interface for the chatbot, providing methods
 * to build messages, errors, and task-related information to the user.
 * It manages all interactions with the user by returning strings that can be printed or logged elsewhere.
 */
public class Ui {

    /**
     * Builds a greeting message when the chatbot starts.
     *
     * @return the greeting message as a string.
     */
    public String greet() {
        return "Hello! I'm MrIncredible\n"
                + "What can I do for you?";
    }

    /**
     * Builds a farewell message when the user exits the chatbot.
     *
     * @return the farewell message as a string.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Builds an error message to the user.
     *
     * @param errorMessage the error message to be built.
     * @return the error message as a string.
     */
    public String handleError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

    /**
     * Builds a message indicating that a task has been removed, and shows the remaining task count.
     *
     * @param task      the task that was removed.
     * @param taskCount the current number of tasks after the removal.
     * @return the task removed message as a string.
     */
    public String showTaskRemoved(Task task, int taskCount) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Builds an error message indicating that the provided task ID is invalid.
     *
     * @return the invalid task ID error message as a string.
     */
    public String showInvalidTaskIdError() {
        return "Invalid task ID.";
    }

    /**
     * Builds a message indicating that a task has been added, and shows the updated task count.
     *
     * @param task      the task that was added.
     * @param taskCount the current number of tasks after the addition.
     * @return the task added message as a string.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Builds an error message indicating that the date and time format is invalid.
     * Informs the user of the correct format.
     *
     * @return the date time parse error message as a string.
     */
    public String showDateTimeParseError() {
        return "Error: Invalid date and time format. Please use 'yyyy-MM-dd HH:mm'.";
    }

    /**
     * Builds a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     * @return the task marked message as a string.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Builds the list of all tasks currently stored by delegating the task listing
     * to the {@code TaskStorage} class.
     *
     * @param taskStorage the {@code TaskStorage} object that contains the tasks to be listed.
     * @return the list of tasks as a string.
     */
    public String showTaskList(TaskStorage taskStorage) {
        StringBuilder sb = new StringBuilder();
        sb.append(taskStorage.listTasks());
        return sb.toString();
    }

    /**
     * Builds a list of tasks that match a given keyword.
     *
     * @param foundTasks a map of tasks that match the search keyword.
     * @return the list of found tasks as a string.
     */
    public String showFoundTasks(Map<Integer, Task> foundTasks) {
        StringBuilder sb = new StringBuilder();
        if (foundTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (Map.Entry<Integer, Task> entry : foundTasks.entrySet()) {
                sb.append("  ").append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
            }
        }
        return sb.toString();
    }
}
