package lexi.ui;

import java.util.ArrayList;

import lexi.task.Task;


/**
 * Handles all interactions with the user.
 * This class is responsible for displaying messages to the user and reading input from the user.
 */
public class Ui {

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskSize The total number of tasks in the list after adding this task.
     */
    public String showAddMessage(Task task, int taskSize) {
        return "Got it. I've added this task:\n"
                + String.format("   %s%n", task)
                + String.format("Now you have %d task%s in the list.%n", taskSize, taskSize == 1 ? "" : "s");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskSize The total number of tasks remaining in the list after deleting this task.
     */
    public String showDeleteMessage(Task task, int taskSize) {
        return "Noted. I've removed this task:\n"
                + task
                + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskSize);
    }

    /**
     * Displays an error message if there was an issue loading data.
     */
    public void showLoadingError() {
        System.out.println("Something went wrong with loading data!\n");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param taskToBeMarked The task that was marked as done.
     */
    public String showMarkMessage(Task taskToBeMarked) {
        return "Nice! I've marked this task as done:\n"
                + "  " + taskToBeMarked;
    }

    /**
     * Displays a message confirming that a task has been unmarked (marked as not done).
     *
     * @param taskToBeMarked The task that was unmarked.
     */
    public String showUnmarkMessage(Task taskToBeMarked) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + taskToBeMarked;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showListOfTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list!";
        } else {
            StringBuilder response = new StringBuilder();
            response.append(" Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                response.append(String.format("  %d. %s%n", i + 1, currTask));
            }
            return response.toString();
        }
    }

    /**
     * Displays the list of matching tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showListOfMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currtask = tasks.get(i);
            response.append(String.format("  %d. %s%n", i + 1, currtask));
        }
        return response.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return "Error: " + message;
    }
}
