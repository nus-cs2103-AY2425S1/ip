package hana;

import hana.task.Task;
import hana.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Scanner;
import java.util.List;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private StringBuilder responseMessage;

    public Ui() {
        responseMessage = new StringBuilder();
    }

    public String getResponseMessage() {
        String message = responseMessage.toString();
        responseMessage.setLength(0);  // Clear the message after returning
        return message;
    }

    /**
     * Displays the welcome message to the user.
     */
    public static String showWelcome() {
        String message = "Hello I'm Hana. \n" + "What can I do for you?\n";
        return message;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public StringBuilder showGoodbye() {
        return responseMessage.append("Bye. Hope to see you again soon!\n");
    }

    /**
     * Reads the command input from the user.
     *
     * @return The command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message indicating that loading the task list failed.
     */
    public StringBuilder showLoadingError() {
        return responseMessage.append("OOPS!!! Error loading the task list.");
    }

    /**
     * Displays the specified error message.
     *
     * @param message The error message to display.
     */
    public StringBuilder showError(String message) {
        return responseMessage.append(message);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public StringBuilder showTaskAdded(Task task, int taskCount) {
        return responseMessage.append("Got it. I've added this task:\n" +
                "      " + task + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public StringBuilder showTaskDeleted(Task task, int taskCount) {
        return responseMessage.append("Noted. I've removed this task:\n" +
                "      " + task + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public StringBuilder showTaskMarked(Task task) {
        return responseMessage.append("Nice! I've marked this task as done:\n" +
                "     " + task);
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public StringBuilder showTaskList(TaskList tasks) {
        responseMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            responseMessage.append("    " + (i + 1) + "." + tasks.get(i) + "\n");
        }
        return responseMessage;
    }

    public StringBuilder showFindResults(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return responseMessage.append("No matching tasks found.");
        } else {
            responseMessage.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                responseMessage.append("    " + (i + 1) + "." + foundTasks.get(i) + "\n");
            }
            return responseMessage;
        }
    }
}

