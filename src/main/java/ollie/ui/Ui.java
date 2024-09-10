package ollie.ui;

import java.util.ArrayList;

import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * The Ui class handles all interactions with the user.
 */
public class Ui {

    /**
     * Greets the user with a welcome message.
     *
     * @return The greeting message.
     */
    public static String greet() {
        return "Hello there! ☺ I'm OLLIE ☺\n" + "What can I do for you today? ☺";
    }

    /**
     * Displays an error message indicating a failure to load tasks.
     *
     * @return The error message.
     */
    public static String showError() {
        return "Oh no! There is an error!\n";
    }

    /**
     * Bids farewell to the user with a goodbye message.
     *
     * @return The goodbye message.
     */
    public static String exit() {
        return "Bye. Have a great day. ☺\n" + "Hope to see you again soon! ☺";
    }

    /**
     * Shows the error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Shows the error message.
     *
     * @param error The error message.
     */
    public static String returnErrorAsString(OllieException error) {
        return error.toString();
    }

    /**
     * Shows the empty description error message based on the task type.
     */
    public static String showEmptyDescriptionError(String taskType) {
        return "Please add a name for a " + taskType + " task!";
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param task The task to add.
     * @param taskNumber The number of tasks in the list.
     */
    public static String addTask(Task task, int taskNumber) {
        return "Got it. I've added this task ☺:\n  " + task.toString() + "\nNow you have " + taskNumber
                + " tasks in the list. ☺";
    }

    /**
     * Deletes a Task object from the list of tasks.
     *
     * @param task The task to delete.
     * @param taskNumber The number of tasks in the list.
     */
    public static String deleteTask(Task task, int taskNumber) {
        return "Noted. I've removed this task:\n  " + task.toString() + "\nNow you have " + taskNumber
                + " tasks in the list.";
    }

    /**
     * Lists all the tasks in the list.
     *
     * @param tasks The list of tasks.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list ☺ :\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to mark as done.
     */
    public static String markTaskAsDone(Task task) {
        return "Nice! ☺ I've marked this task as done ☺ :\n  " + task.toString();
    }

    /**
     * Marks a task as not done.
     *
     * @param task The task to mark as not done.
     */
    public static String unmarkTaskAsDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Displays the search results for the find command.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public static String showFindResults(ArrayList<Task> matchingTasks) {
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("There are no matching tasks in your list.");
        } else {
            output.append("Here are the matching tasks in your list ☺ :\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append(i + 1).append(".").append(matchingTasks.get(i).toString()).append("\n");
            }
        }
        return output.toString();
    }
}
