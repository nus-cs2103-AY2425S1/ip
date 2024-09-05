package ui;

import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * The {@code Ui} class handles interactions with the user, including displaying messages,
 * showing the list of tasks, and reading user input. It acts as the user interface for
 * the application.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public static String showWelcome() {
        return "Hello! I'm Downy.\nHow can I help?\n";
    }

    /**
     * Displays an exit message to the user and closes the scanner.
     */
    public String showExitMessage() {
        this.scanner.close();
        return "Bye! Yippee!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static String showErrorMessage(String message) {
        return "Error: " + message + "\n";
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to be displayed.
     */
    public static String showMessage(String message) {
        return message + "\n";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list containing the tasks to be displayed.
     */
    public String displayTasks(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            result.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }

        return result.toString();
    }

    /**
     * Displays tasks from the task list that contain the specified keyword in their names.
     * The search is case-insensitive, and only matching tasks are displayed. If no tasks
     * match the keyword, a message indicating that no matching tasks were found is shown.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be searched.
     * @param keyword The keyword to search for within the task names.
     */
    public String displayMatchingTasks(TaskList tasks, String keyword) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list that match the keyword:\n");

        String lowerCaseKeyword = keyword.toLowerCase();
        int matchCount = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            String taskName = tasks.getTask(i).getName().toLowerCase();

            if (taskName.contains(lowerCaseKeyword)) {
                result.append((matchCount + 1)).append(". ").append(tasks.getTask(i)).append("\n");
                matchCount++;
            }
        }

        if (matchCount == 0) {
            result.append("No matching tasks found.\n");
        }

        return result.toString();
    }

    /**
     * Displays a message indicating that a task is complete.
     *
     * @param t The task that is complete.
     */
    public String displayCompletedTask(Task t) {
        return "Nice! You've completed this task:\n  " + t + "\n";
    }

    /**
     * Displays a message indicating that a task is not complete.
     *
     * @param t The task that is not complete.
     */
    public String displayIncompleteTask(Task t) {
        return "Ok! This task is not complete:\n  " + t + "\n";
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param t The task that has been deleted.
     */
    public String displayDeletedTask(Task t) {
        return "Ok! This task has been removed:\n  " + t + "\n";
    }

    /**
     * Displays a message indicating that a task has been added and shows the current
     * number of tasks in the list.
     *
     * @param t         The task that has been added.
     * @param taskCount The current number of tasks in the list.
     */
    public String displayTaskAdded(Task t, int taskCount) {
        return "Okay! Added this task:\n  " + t + "\nNow you have " + taskCount + " tasks in this list\n";
    }

    /**
     * Reads and returns the next command input by the user.
     *
     * @return The command input by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a help message listing all valid commands.
     */
    public String displayHelp() {
        StringBuilder result = new StringBuilder();
        result.append("Here are a list of valid commands:\n");
        result.append(" - list\n");
        result.append(" - mark <taskNumber>\n");
        result.append(" - unmark <taskNumber>\n");
        result.append(" - delete <taskNumber>\n");
        result.append(" - todo <taskDescription>\n");
        result.append(" - deadline <taskDescription> /by <dueDate>\n");
        result.append(" - event <taskDescription> /from <startTime> /to <endTime>\n");
        result.append(" - bye\n");
        result.append(" - help\n");
        result.append(" - find <keyword>\n");

        return result.toString();
    }
}
