package mahesh.util;

import mahesh.task.Task;

/**
 * Utility class for handling user interface interactions.
 */
public class Ui {

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task the task that was added
     * @param taskCount the current number of tasks in the list
     * @return a String message indicating the task was added
     */
    public static String printTaskAdded(Task task, int taskCount) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:");
        response.append(String.format("  %s", task));
        response.append(String.format("Now you have %d tasks in the list.", taskCount));
        return response.toString();
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param taskCount the current number of tasks in the list
     * @return a String message indicating the task was deleted
     */
    public static String printTaskDeleted(Task task, int taskCount) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've removed this task:");
        response.append(String.format("  %s", task));
        response.append(String.format("Now you have %d tasks in the list.", taskCount));
        return response.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     * @return a String message indicating the task was marked as done
     */
    public static String printMarkedAsDone(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as done:\n  ");
        response.append(task);
        return response.toString();
    }

    /**
     * Returns a message indicating that a task has been unmarked as done.
     *
     * @param task the task that was unmarked as done
     * @return a String message indicating the task was unmarked as done
     */
    public static String printUnmarkedAsDone(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("OK, I've marked this task as not done yet:\n  ");
        response.append(task);
        return response.toString();
    }

    /**
     * Returns an error message.
     *
     * @param err the exception containing the error message
     * @return a String error message
     */
    public static String printErr(MaheshException err) {
        return err.getMessage();
    }

    /**
     * Returns an error message indicating that there is no such task.
     *
     * @param taskCount the current number of tasks in the list
     * @return a String error message indicating there is no such task
     */
    public static String printNoSuchTaskErr(int taskCount) {
        StringBuilder response = new StringBuilder();
        response.append(String.format("There is no such task. You currently have %d task(s).\n", taskCount));
        response.append("Use the \"list\" command to view all your tasks.");
        return response.toString();
    }

    /**
     * Returns an error message indicating that the command is incomplete or incorrect.
     *
     * @param err the exception containing the error message
     * @return a String error message indicating the command is incomplete or incorrect
     */
    public static String printIncompleteCommandErr(MaheshException err) {
        StringBuilder response = new StringBuilder();
        response.append("The command is incomplete/incorrect.\n");
        response.append(err.getMessage());
        return response.toString();
    }

    /**
     * Returns an error message indicating that the task list is empty.
     *
     * @return a String error message indicating the task list is empty
     */
    public static String printEmptyListErr() {
        return "You have no tasks! Add a few tasks (todo, deadline or event)";
    }

    /**
     * Returns an error message indicating that some tasks may not have loaded correctly due to a corrupted data file.
     *
     * @return a String error message indicating corrupted data file
     */
    public static String printCorruptedDataErr() {
        return "Some tasks may not have loaded correctly due to corrupted data file.";
    }
}
