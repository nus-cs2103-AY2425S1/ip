package bob;

import java.util.ArrayList;

/**
 * Represents the user interface of the Bob chatbot.
 * The Ui class handles all user interactions, displaying messages and formatting output.
 * It provides methods to show welcome and goodbye messages, display tasks, handle errors,
 * and show results based on user input.
 */
public class Ui {

    /**
     * Returns the welcome message when the bot starts.
     *
     * @param botName The name of the chatbot.
     * @return The formatted welcome message.
     */
    public String showWelcomeMessage(String botName) {
        return String.format("Hello! I'm %s!\n", botName);
    }

    /**
     * Returns the goodbye message when the bot ends.
     *
     * @return The formatted goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns a horizontal line for formatting output in the UI.
     *
     * @return The horizontal line as a string.
     */
    public String showLine() {
        return "____________________________________________________________\n";
    }

    /**
     * Returns a message when a task is added to the list.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     * @return A message indicating that the task was added and the new list size.
     */
    public String showTaskAdded(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(String.format("  %s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.\n", size));
        return sb.toString();
    }

    /**
     * Returns a message when a task is deleted from the list.
     *
     * @param deleted The string representation of the deleted task.
     * @param size The new size of the task list.
     * @return A message indicating that the task was deleted and the new list size.
     */
    public String showTaskDeleted(String deleted, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(deleted).append("\n");
        sb.append(String.format("Now you have %d tasks in the list.\n", size));
        return sb.toString();
    }

    /**
     * Returns an error message.
     *
     * @param errorMessage The error message to be displayed.
     * @return The formatted error message.
     */
    public String showError(String errorMessage) {
        return "Error: " + errorMessage + "\n";
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param mark The string representation of the marked task.
     * @return A message indicating that the task was marked as done.
     */
    public String showTaskMarked(String mark) {
        return mark + "\n";
    }

    /**
     * Returns a message when a task is unmarked as done.
     *
     * @param unmark The string representation of the unmarked task.
     * @return A message indicating that the task was unmarked as done.
     */
    public String showTaskUnmarked(String unmark) {
        return unmark + "\n";
    }

    /**
     * Returns a list of tasks that match the search key.
     *
     * @param tasksWithKey An ArrayList of tasks that contain the keyword in their descriptions.
     * @return A message listing tasks that match the search key.
     */
    public String showTasksFound(ArrayList<Task> tasksWithKey) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksWithKey.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasksWithKey.get(i)));
        }
        return sb.toString();
    }
}
