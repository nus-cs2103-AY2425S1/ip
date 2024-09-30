package BRMO;

import BRMO.task.Task;
import BRMO.task.TaskList;

/**
 * Represents the User Interface (UI) class for BRMO chatbot.
 * This class handles interactions between the user and the application,
 * providing responses and showing results of actions such as adding,
 * removing, or listing tasks.
 */
public class Ui {

    /**
     * Constructs a Ui object to handle user interactions.
     */
    public Ui() {}

    /**
     * Returns a welcome message when the application is started.
     *
     * @return A welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm BetaRMinusOne; a.k.a. BRMO.";
    }

    /**
     * Returns a goodbye message when the application is closed.
     *
     * @return A goodbye message.
     */
    public String showGoodbye() {
        return "We shall meet again.";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to display.
     * @return A formatted error message.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Returns the list of tasks in the task list.
     * If the task list is empty, a message indicating that is returned.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A formatted string showing the tasks in the task list.
     * @throws InvalidCommandException If the task list cannot be retrieved.
     */
    public String showTasks(TaskList taskList) throws InvalidCommandException {
        assert taskList != null : "TaskList should not be null";
        if (taskList.size() == 0) {
            return "There are no tasks in your list.";
        }

        return "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param type The type of task that was added (e.g., deadline, event).
     * @return A formatted message showing the added task.
     */
    public String showTaskAdded(Task task, String type) {
        return "Added the following " + type + ":\n" + task;
    }

    /**
     * Returns a message confirming that a task has been removed.
     *
     * @param task The task that was removed.
     * @return A formatted message showing the removed task.
     */
    public String showTaskRemoved(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A formatted message showing the completed task.
     */
    public String showTaskMarked(Task task) {
        assert task.isMarked() : "Task should be marked as done";
        return "Nice. The following task has been marked as done:\n" + task;
    }

    /**
     * Returns a message confirming that a task has been marked as undone.
     *
     * @param task The task that was marked as undone.
     * @return A formatted message showing the unmarked task.
     */
    public String showTaskUnmarked(Task task) {
        assert !task.isMarked() : "Task should be marked as undone";
        return "Nice. The following task has been marked as undone:\n" + task;
    }

    /**
     * Returns a list of tasks that match the user's search criteria.
     * If no tasks are found, a message indicating that is returned.
     *
     * @param tasks The TaskList containing the found tasks.
     * @return A formatted message showing the matching tasks.
     */
    public String showFoundTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            return "No tasks found.";
        }
        return "Here are the matching tasks in your list:\n" + tasks;
    }
}