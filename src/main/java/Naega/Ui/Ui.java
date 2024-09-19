package Naega.Ui;

import Naega.Task.Task;
import java.util.List;
import java.util.Scanner;

public class Ui {

    /**
     * Returns a welcome message to the user.
     * This method returns a greeting and prompts the user for input.
     *
     * @return the welcome message
     */
    public String showWelcome() {
        return """
                ____________________________________________________________
                 Hello! I'm Naega
                 What can I do for you?
                ____________________________________________________________""";
    }

    /**
     * Returns a separator line.
     * This method is used to improve the readability of output.
     *
     * @return the separator line
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns an error message when loading tasks fails.
     * This method informs the user that there was an issue loading tasks from a file.
     *
     * @return the error message
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

    /**
     * Returns an error message with a custom message.
     * This method returns a message indicating an error occurred.
     *
     * @param message the error message to display
     * @return the error message with details
     */
     public String showError(String message) {
        return "Oops! " + message;
    }

    /**
     * Reads a command from the user input.
     * (Note: Since this is reading input, it can stay as is, but not used for the GUI.)
     *
     * @return the line of text entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Returns a message indicating a task has been added.
     * This method returns the added task and the updated number of tasks in the list.
     *
     * @param task the task that was added
     * @param size the updated number of tasks in the list
     * @return the message for the task added
     */
    public String printTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "   " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message indicating a task has been deleted.
     * This method returns the deleted task and the updated number of remaining tasks.
     *
     * @param task the task that was removed
     * @param remainingTasks the updated number of tasks remaining in the list
     * @return the message for the task deleted
     */
    public String showDeletedTask(Task task, int remainingTasks) {
        return "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + "Now you have " + remainingTasks + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * Returns the tasks that match a search query.
     * This method returns the list of tasks that match the search criteria or a message indicating no tasks were found.
     *
     * @param tasks the list of tasks that match the search criteria
     * @return the message for the found tasks
     */
    public String showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "There are no matching tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}