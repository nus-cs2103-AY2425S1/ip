package weeny.ui;

import java.util.List;
import java.util.Scanner;

import weeny.task.Task;


/**
 * Handles user interactions by displaying messages and reading input.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcomeMessage() {
        return String.format("Hello! I'm Weeny\nWhat can I do for you?\n");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        String taskList = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            taskList = taskList + String.format((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return String.format(taskList);
    }

    /**
     * Diplays the list of tasks after search
     *
     * @param tasks A list of tasks that contains the keyword
     */
    public String showSearchResult(List<Task> tasks) {
        assert tasks != null : "Search result list should not be null";
        String resultList = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            resultList = resultList + String.format((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return String.format(resultList);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks.
     */
    public String printTaskAddedMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        return String.format("Got it. I've added this task:\n" + task.toString() +
                "\n" +"Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated number of tasks.
     */
    public String showTaskDeletedMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        return String.format("Spooof! The task magically disappeared:\n" +
                task.toString() + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkMessage(Task task) {
        assert task != null : "Task should not be null";
        return String.format("OK, I've marked this task as not done yet:\n" +
                task.toString() + "\n");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMarkMessage(Task task) {
        assert task != null : "Task should not be null";
        return String.format("Nice! I've marked this task as done:\n" +
                task.toString() + "\n");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return String.format("Error:" + message + "\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbyeMessage() {
        return String.format("Bye. Hope to see you soon!\n");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The user input.
     */
    public String readUserInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    /**
     * Prints a line separator.
     */
    private String stringLine() {
        return "______________________________________________\n";
    }
}
