package muller.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import muller.command.MullerException;
import muller.task.Task;
import muller.task.TaskList;

/**
 * Handles all user interactions, including displaying messages and reading user input.
 */
public class Ui {
    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm Muller\nWhat can I do for you?";
    }
    /**
     * Displays a confirmation of the task added.
     * @param task The task to add.
     * @param tasks The list of tasks currently on the list.
     */
    public String showTaskAdded(Task task, TaskList tasks) {
        return ("Got it. I've added this task:\n"
                    + "  " + task
                    + "\nNow you have " + tasks.getSize()
                    + " tasks in the list.");
    }
    /**
     * Displays a confirmation of the task deleted.
     * @param tasks The list of tasks currently on the list.
     * @param index The index of the respective task.
     */
    public String showTaskDeleted(TaskList tasks, Task deletedTask, int index) {
        return ("Noted. I've removed this task:\n"
                + "  " + deletedTask
                + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }
    /**
     * Displays a confirmation of the task marked.
     * @param tasks The list of tasks currently on the list.
     * @param index The index of the respective task to mark.
     */
    public String showTaskMarked(TaskList tasks, int index) {
        try {
            return ("Nice! I've marked this task as done:\n"
                    + "  " + tasks.get(index));
        } catch (MullerException e){
            return e.getMessage();
        }
    }
    /**
     * Displays a confirmation of the task unmarked.
     * @param tasks The list of tasks currently on the list.
     * @param index The index of the respective task to unmark.
     */
    public String showTaskUnMarked(TaskList tasks, int index) {
        try {
            return ("OK, I've marked this task as not done yet:\n"
                    + "  " + tasks.get(index));
        } catch (MullerException e){
            return e.getMessage();
        }
    }
    /**
     * Displays the task on specific dates.
     * @param tasks The list of tasks currently on the list.
     * @param date The date of the task to search.
     */
    public String showTaskOnDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on " + date.format(Task.OUTPUT_DATE_FORMATTER) + ":");
        boolean isFound = false;
        for (Task task : tasks.getTasks()) {
            if (task.isOnDate(date)) {
                sb.append("\n  " + task);
                isFound = true;
            }
        }
        if (!isFound) {
            return "  No tasks found.";
        }
        return sb.toString();
    }
    /**
     * Displays an error message when there is an issue loading tasks.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
    /**
     * Displays a specified error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String showTaskList(TaskList tasks) {
        try {
            if (tasks.isEmpty()) {
                return "No tasks found.";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the tasks in your list:\n");
                for (int i = 1; i <= tasks.getSize(); i++) {
                    sb.append(i + ": " + tasks.get(i - 1) + "\n");
                }
                return sb.toString();
            }
        } catch (MullerException e) {
            return showError(e.getMessage());
        }
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= matchingTasks.size(); i++) {
                sb.append(i + ": " + matchingTasks.get(i - 1) + "\n");
            }
            return sb.toString();
        }
    }
}
