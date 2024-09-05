package nether;

import nether.task.Task;
import nether.task.TaskList;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including displaying messages and reading user input.
 *
 * The {@code Ui} class mainly provides methods to print messages, read/parse commands from the user,
 * and display information related to the application's operation.
 */

public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object with a {@link Scanner} to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the Nether logo and welcome message.
     * This method is called when the application starts.
     */
    public void printWelcome() {
        String logo = " _   _      _   _        \n"
                + "| \\ | | ___| |_| |__  ___ _ __ \n"
                + "|  \\| |/ _ \\ __| '_ \\/ _ \\ '__|\n"
                + "| |\\  |  __/ |_| | | ||__/ |  \n"
                + "|_| \\_|\\___|\\__|_| |_\\___|_|\n";

        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello sir! I'm Nether");
        System.out.println("What can I do for you today?");
        printHorizontalLine();
    }

    /**
     * Reads the command input by the user, trimming the input off leading or trailing whitespaces
     *
     * @return Input string provided by the user without leading or trailing whitespaces
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints the exit message to be shown when program is closed.
     */
    public String printExitMessage() {
        return "Bye. If you need any more help in the future, feel free to ask me. Enjoy your day!";
    }

    /**
     * Prints a message for the user when an invalid or unregistered command is used.
     *
     * @param message message The error message explaining the issue.
     */
    public void printError(String message) {
        System.out.println("Sir, " + message);
        printHorizontalLine();
    }

    /**
     * Prints out a long horizontal line to act as separator in the chat.
     */
    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message indicating that a task has been successfully added to the task list.
     *
     * @param task The task that was added.
     */
    public String printTaskAdded(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("  ").append(task.toString());
        return response.toString();
    }

    /**
     * Prints a message indicating that a task has been successfully deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list after deletion.
     */
    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    public String printTaskDeleted(Task task, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Noted, I've removed this task from the list:\n");
        response.append("  ").append(task.toString()).append("\n");
        response.append("Now you have " + size + " task" + (size > 1 ? "s" : "") + " in the list.");
        return response.toString();
    }

    /**
     * Prints a message when a task is marked or unmarked, showing the task's new status.
     *
     * @param taskToMark The task that was marked or unmarked.
     * @param markMessage The message describing the action (e.g., marked as done).
     */
    public String printMarkMessage(Task taskToMark, String markMessage) {
        StringBuilder response = new StringBuilder();
        response.append(markMessage).append("\n");
        response.append("  ").append(taskToMark.toString());
        return response.toString();
    }

    public String printMatchingTasks(TaskList matchingTasks) {
        StringBuilder response = new StringBuilder();
        if (matchingTasks.getSize() > 0) {
            response.append("Here are the tasks that match your search in your list:\n");
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                response.append((i + 1)).append(".").append(matchingTasks.getTask(i).toString()).append("\n");
            }
            return response.toString();
        } else {
            return "No matching tasks found in your list.";
        }
    }
}
