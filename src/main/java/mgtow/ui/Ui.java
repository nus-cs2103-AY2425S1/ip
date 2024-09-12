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

    public Ui() {
        scanner = new Scanner(System.in);
    }

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

    public void showError(String message) {
        System.out.println(message);
    }

    public String getGoodbyeMessage() {
        return "OK bye time to MGTOW";
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getTaskListString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String getUnmarkTaskMessage(Task task) {
        return "OK, I've unmarked this task:\n  " + task;
    }

    public String getDeleteTaskMessage(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n  " + task +
                "\nNow you have " + remainingTasks + " tasks in the list.";
    }

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

    public String getErrorMessage(String message) {
        return "Error: " + message;
    }

    public String getSortedTaskListString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list, sorted by date:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

}