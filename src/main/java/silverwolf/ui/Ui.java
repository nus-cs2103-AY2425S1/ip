package silverwolf.ui;

import java.util.List;
import java.util.Scanner;

import silverwolf.task.Task;

/**
 * The Ui class handles user interactions with the application.
 * It is responsible for displaying messages to the user and reading user input.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message indicating that an error occurred during loading.
     * @return the error message.
     */
    public String showLoadingError() {
        return "An error occured whilst loading data";
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays an error message indicating that an error occurred when reading the file.
     * @return error message that the file has errors.
     */
    public String showFileError() {
        return "The file has errors";
    }
    /**
     * Displays a goodbye message to the user when the application exits.
     * @return Outputs the message to the chat bot.
     */
    public String showGoodbye() {
        return " Till we meet again! May this journey lead us starward!";
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return outputToGui Outputs the message to the chat bot.
     */
    public String showUnmarkTask(Task task) {
        String outputToGui;
        outputToGui = "OK, I've marked this task as not done yet:\n";
        outputToGui = outputToGui + task;
        return outputToGui;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return outputToGui Outputs the message to the chat bot.
     */
    public String showMarkTask(Task task) {
        String outputToGui;
        outputToGui = "Nice! I've marked this task as done:";
        outputToGui = outputToGui + task;
        return outputToGui;
    }

    /**
     * Displays the tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search.
     * @return outputToGui Outputs the message to the chat bot.
     */
    public String showMatchingTasks(List<Task> tasks) {
        StringBuilder outputToGui;
        if (tasks.isEmpty()) {
            outputToGui = new StringBuilder("No tasks found with the given keyword.");
        } else {
            outputToGui = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                outputToGui.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        }
        return outputToGui.toString();
    }
}
