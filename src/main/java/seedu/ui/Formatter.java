package seedu.ui;

import java.util.ArrayList;

import seedu.task.Task;

/**
 * The {@code Ui} class handles all interactions with the user. It provides methods to display
 * various messages related to tasks and application events.
 */
public class Formatter {
    /**
     * Displays the introductory messages when the application starts.
     */
    public String introduceBobUi() {
        return "Hello! I'm Bob! \n" + "What can I do for you?";
    }

    /**
     * Displays the exit message when the application is about to close.
     */
    public String exitBobUi() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param t The task that was added.
     * @param size The current size of the task list after adding the task.
     */
    public String addTaskUi(Task t, int size) {
        return "Got it. I've added this task: \n" + t.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that was marked as done.
     */
    public String markTaskUi(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     */
    public String unmarkTaskUi(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param t The task that was removed.
     * @param size The current size of the task list after removing the task.
     */
    public String deleteTaskUi(Task t, int size) {
        return "Noted. I've removed this task: \n" + t.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that an error occurred while trying to save tasks.
     */
    public void savingErrorUi() {
        System.out.println("An error has occurred when trying to save.");
    }

    public String listTaskUi(ArrayList<Task> tasks) {
        String message = "";
        if (tasks.isEmpty()) {
            message = "No items yet!";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                message += (i + 1) + ". " + tasks.get(i) + "\n";
            }
        }
        return message;
    }
}
