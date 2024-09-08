package drbrown.utils;

import java.util.Scanner;

import drbrown.task.Task;

/**
 * Handles the user interface for the DrBrown application.
 * Provides methods to interact with the user by displaying messages and reading input.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the greeting message to the user when the application starts.
     *
     * @return The greeting message as a String.
     */
    public String showGreeting() {
        return "Roads? Where We're Going, We Don't Need Roads?! So, what can I help you with today?";
    }

    /**
     * Displays the end message to the user when the application is closing.
     *
     * @return The end message as a String.
     */
    public String showEnd() {
        return "Your future is whatever you make it, so make it a good one! Until next time, keep the DeLorean ready!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     * @return The error message as a String.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a message confirming the creation of a new task.
     *
     * @param task The task that was created.
     * @return A string message confirming the task creation.
     */
    public String showTaskCreation(TaskList tasks, Task task) {
        return task.toUiString() + "\n" + task + "\n" + this.showCount(tasks);
    }

    /**
     * Displays a message confirming the marking of a task as completed.
     *
     * @param markTask The task that was marked as completed.
     * @return A string message confirming the task has been marked as completed.
     */
    public String showMarkTask(Task markTask) {
        return "Task complete! If my calculations are correct, when this baby hits 88 miles per hour..."
                + " you're gonna see some serious progress!\n\n" + markTask;
    }

    /**
     * Displays a message confirming the unmarking of a task as incomplete.
     *
     * @param unmarkTask The task that was unmarked as incomplete.
     * @return A string message confirming the task has been unmarked as incomplete.
     */
    public String showUnmarkTask(Task unmarkTask) {
        return "Looks like we're going back to the future—task unmarked! Time to revisit this one.\n\n" + unmarkTask;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @return A string message introducing the task list.
     */
    public String showList() {
        return "Here's your list! Like Doc Brown's flux capacitor, this will help keep everything in perfect sync!\n\n";
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param deleteTask The task that was deleted.
     * @return A string message confirming the deletion of the task.
     */
    public String showDeleteTask(TaskList tasks, Task deleteTask) {
        return "That task's history has been erased! Just like Marty's fading photo - it's gone, "
                + "like it never existed!\n\n" + deleteTask + "\n" + this.showCount(tasks);
    }

    /**
     * Displays the total count of tasks in the list.
     *
     * @param tasks The TaskList containing the current tasks.
     * @return A string message displaying the total count of tasks.
     */
    public String showCount(TaskList tasks) {
        return "\nYour total count is now " + tasks.getCount() + "! Like the time circuits, "
                + "everything's in sync - keep those tasks ticking along!";
    }

    /**
     * Displays a default message when the input is unrecognized.
     *
     * @return A string message indicating unrecognized input.
     */
    public String showDefault() {
        return "I'm from the future, and even I don't know what that means.";
    }

    /**
     * Displays the matching tasks found in the list.
     *
     * @return A string message introducing the matching tasks.
     */
    public String showFind() {
        return "Here are the matching tasks in your list — it's your density... "
                 + "I mean, your destiny to get these done!\n";
    }
}
