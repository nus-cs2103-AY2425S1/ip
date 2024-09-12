package bob.Ui;

import java.util.ArrayList;

import bob.Tasks.Task;

/**
 * The Ui class of Bob chatbot is responsible for handling all user interactions.
 * This class provides methods to display messages, list of Tasks, and handling of user
 * input and output.
 */

public class Ui {

    /**
     * Displays welcome message and logo when the chatbot starts.
     */
    public String showWelcome() {
        return "Bello! I'm Bob!\nHow can I help you today?";
    }

    /**
     * Displays goodbye messsage when the chatbot exits.
     */
    public String showGoodbye() {
        return "Bye, see you again :)";
    }

    /**
     * Displays error message when there is an error.
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return "Wait a minute..." + message;
    }

    /**
     * Displays the current task list.
     * @param tasks an ArrayList of Task objects.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the Tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Displays the message signaling user that a task has been successfully added into the task list.
     * @param task The Task object that was added.
     * @param size The total number of Tasks in the task list currently.
     */
    public String showAddedTask(Task task, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("    ").append(task).append("\n");
        response.append("Now you have ").append(size).append(" task(s) in the list.");
        return response.toString();
    }

    /**
     * Displays the message signaling user that a task has been successfully removed from the task list.
     * @param task The Task object that was removed.
     * @param size The total number of Tasks in the task list currently.
     */
    public String showRemovedTask(Task task, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Noted, I've removed this task from the list:\n");
        response.append("  ").append(task).append("\n");
        response.append("Now you have ").append(size).append(" task(s) in the list.");
        return response.toString();
    }

    public String showTaskMarked(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Yay! I've marked this task as done:\n");
        response.append("    ").append(task);
        return response.toString();
    }

    public String showTaskUnmarked(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Alright, I've marked this task as not done yet:\n");
        response.append("    ").append(task);
        return response.toString();
    }

    public String showMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching Tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString());
        }
        return response.toString();
    }

}
