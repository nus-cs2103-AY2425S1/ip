package Main;

import Tasks.Task;
import Tasks.TaskList;

/**
 * Handles user interface interactions for the Diego application.
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return The welcome message as a String.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Diego\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message as a String.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the list of tasks as a String.
     *
     * @param tasks The task list to be displayed.
     * @return The task list as a String.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The message as a String.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been unmarked as not done.
     *
     * @param task The task that has been unmarked.
     * @return The message as a String.
     */
    public String showTaskUnmarked(Task task) {
        return "Ok! I've marked this task as not done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task      The task that has been added.
     * @param taskCount The current number of tasks in the task list.
     * @return The message as a String.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", task, taskCount);
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task      The task that has been deleted.
     * @param taskCount The current number of tasks in the task list.
     * @return The message as a String.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list", task, taskCount);
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be displayed.
     * @return The error message as a String.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns the list of tasks that match the search keyword.
     *
     * @param tasks The task list containing the matching tasks.
     * @return The list of matching tasks as a String.
     */
    public String showFoundTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
