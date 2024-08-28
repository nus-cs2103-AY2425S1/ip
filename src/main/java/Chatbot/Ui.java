package Chatbot;

import Chatbot.TaskStorage;
import Tasks.Task;

/**
 * The {@code Ui} class handles the user interface for the chatbot, providing methods
 * to display messages, errors, and task-related information to the user.
 * It manages all interactions with the user by printing messages to the console.
 */
public class Ui {

    /**
     * Displays a greeting message when the chatbot starts.
     */
    public void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("Hello! I'm MrIncredible");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a farewell message when the user exits the chatbot.
     */
    public void sayBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage the error message to be displayed.
     */
    public void handleError(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    OOPS!!! " + errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been removed, and shows the remaining task count.
     *
     * @param task      the task that was removed.
     * @param taskCount the current number of tasks after the removal.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message indicating that the provided task ID is invalid.
     */
    public void showInvalidTaskIdError() {
        System.out.println("    Invalid task ID.");
    }

    /**
     * Displays a message indicating that a task has been added, and shows the updated task count.
     *
     * @param task      the task that was added.
     * @param taskCount the current number of tasks after the addition.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message indicating that the date and time format is invalid.
     * Informs the user of the correct format.
     */
    public void showDateTimeParseError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Error: Invalid date and time format. Please use 'yyyy-MM-dd HH:mm'.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the list of all tasks currently stored by delegating the task listing
     * to the {@code TaskStorage} class.
     *
     * @param taskStorage the {@code TaskStorage} object that contains the tasks to be listed.
     */
    public void showTaskList(TaskStorage taskStorage) {
        System.out.println("    ____________________________________________________________");
        taskStorage.listTasks();
        System.out.println("    ____________________________________________________________");
    }
}
