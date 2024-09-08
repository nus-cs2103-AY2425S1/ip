package pikappi;

import java.util.ArrayList;
import java.util.Scanner;

import pikappi.task.Task;

/**
 * Represents a user interface that interacts with the user.
 */
public class Ui {
    protected static Scanner reader = new Scanner(System.in);

    /**
     * Returns a Ui object that interacts with the user.
     */
    public Ui() {
    }

    /**
     * Bids farewell to the user when the program ends.
     */
    public String sayGoodbye() {
        return "Pi-kapi! See you again~\n";
    }

    /**
     * Shows an error message to the user.
     *
     * @param message Error message to be shown to the user
     */
    public String showErrorMessage(String message) {
        return message;
    }

    /**
     * Shows the added task to the user.
     *
     * @param task Task that was added
     * @param taskCount Number of tasks in the list
     */
    public String showAddedTask(Task task, int taskCount) {
        return "Pi-ka-pipi! I've added this task:\n " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Shows the deleted task to the user.
     *
     * @param task Task that was deleted
     * @param taskCount Number of tasks in the list
     */
    public String showDeletedTask(Task task, int taskCount) {
        return "Pipi-ka-pi! I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Shows the matching tasks to the user.
     *
     * @param matchingTasks TaskList list of tasks that contains matching tasks
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.getTasks().isEmpty()) {
            return "Pika..? No matching tasks found..";
        } else {
            String s = "Pika! Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.getTasks().size(); i++) {
                s += (i + 1) + ". " + matchingTasks.getTasks().get(i) + "\n";
            }
            return s;
        }
    }

    /** Shows the user that there is no task in the list. */
    public String showNoTasks() {
        return "Pika-ka! You have no tasks!";
    }

    /**
     * Shows all tasks in the list to the user.
     *
     * @param tasks TaskList list of tasks to be shown
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                s += (i + 1) + "." + tasks.get(i) + "\n";
            } else {
                break;
            }
        }
        return s;
    }

    /**
     * Shows the task that was marked as done to the user.
     *
     * @param task Task that was marked as done
     */
    public String showMarkedTask(Task task) {
        return "Pika! I've marked this task as done:\n" + task;
    }

    /**
     * Shows the task that was unmarked as not done yet to the user.
     *
     * @param task Task that was unmarked as not done yet
     */
    public String showUnmarkedTask(Task task) {
        return "kaPi! I've unmarked this task as not done yet:\n" + task;
    }

    /**
     * Shows the help message to the user.
     */
    public String showHelp() {
        return "Pika! Here are the commands you can use:\n"
                + "> todo <task>: Adds a todo task to the list\n"
                + "> deadline <task> /by <date>: Adds a deadline task to the list\n"
                + "> event <task> /at <date>: Adds an event task to the list\n"
                + "> list: Shows all tasks in the list\n"
                + "> find <keyword>: Shows tasks that contain the keyword\n"
                + "> mark <index>: Marks a task as done\n"
                + "> unmark <index>: Unmarks a task as not done yet\n"
                + "> delete <index>: Deletes a task from the list\n"
                + "> bye: Exits Pikappi";
    }
}
