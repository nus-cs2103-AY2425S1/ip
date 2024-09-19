package mgtow.ui;

import mgtow.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the Ui in the Mgtow application.
 * This class provides methods for displaying responses to the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message for the application.
     *
     * @return A string containing the welcome message.
     */
    public String getWelcomeMessage() {
        return LINE + "\nHello! I'm MGTOW\nWhat can I do for you?\n" + LINE;
    }

    public void showWelcome() {
        System.out.println(getWelcomeMessage());
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("No existing task file found. Starting with an empty task list.");
    }

    /**
     * Returns the goodbye message for the application.
     *
     * @return A string containing the goodbye message.
     */
    public String getGoodbyeMessage() {
        return "OK bye time to MGTOW";
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Generates a string representation of the task list.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     * @return A formatted string containing all tasks in the list.
     */
    public String getTaskListString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates a message for marking a task as done.
     *
     * @param task The task that has been marked as done.
     * @return A string message confirming the task has been marked as done.
     */
    public String getMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Generates a message for unmarking a task.
     *
     * @param task The task that has been unmarked.
     * @return A string message confirming the task has been unmarked.
     */
    public String getUnmarkTaskMessage(Task task) {
        return "OK, I've unmarked this task:\n  " + task;
    }

    /**
     * Generates a message for deleting a task.
     *
     * @param task The task that has been deleted.
     * @param remainingTasks The number of tasks remaining in the list.
     * @return A string message confirming the task deletion and showing the remaining task count.
     */
    public String getDeleteTaskMessage(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n  " + task +
                "\nNow you have " + remainingTasks + " tasks in the list.";
    }

    /**
     * Generates a message for adding a new task.
     *
     * @param task The task that has been added.
     * @param totalTasks The total number of tasks in the list after addition.
     * @return A string message confirming the task addition and showing the total task count.
     */
    public String getAddTaskMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task +
                "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Gets the tasks found that match the given keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     * @param keyword The keyword used for the search.
     * @return String output of matching tasks.
     */
    public String getFoundTasksMessage(ArrayList<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            return "No matching tasks found for '" + keyword + "'.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Generates an error message.
     *
     * @param message The error message content.
     * @return A formatted error message string.
     */
    public String getErrorMessage(String message) {
        return "Error: " + message;
    }

    /**
     * Generates a string representation of the sorted task list.
     *
     * @param tasks The ArrayList of sorted tasks to be displayed.
     * @return A formatted string containing all sorted tasks in the list.
     */
    public String getSortedTaskListString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list, sorted by date:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

}