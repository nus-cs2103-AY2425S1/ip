package king.ui;

import java.util.ArrayList;
import java.util.Scanner;

import king.KingException;
import king.TaskList;
import king.task.Task;

/**
 * The Ui class handles interactions with the user such as displaying messages.
 */
public class Ui {
    private static final String LOGO = " _  __ _             \n"
            + "| |/ /(_)_ __   __ _ \n"
            + "| ' / | | '_ \\ / _` |\n"
            + "| . \\ | | | | | (_| |\n"
            + "|_|\\_\\|_|_| |_|\\__, |\n"
            + "               |___/ ";

    /**
     * Displays the welcome message with the "King" logo.
     */
    public String showWelcome() {
        return "Behold the wrath of the KING!" + "\n" + "What do you have for me?";
    }

    /**
     * Displays the goodbye message when the application is exiting.
     */
    public String showGoodbye() {
        return "You are dismissed, my humble servant.";
    }

    /**
     * Displays a horizontal line to visually separate sections of the output.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Displays an error message.
     *
     * @param error the exception message to be displayed
     */
    public String showErrorAsString(KingException error) {
        return error.toString();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param taskList the list of tasks to be displayed
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(" ").append(i + 1).append(". ").append(taskList.getTask(i).toString()).append("\n");
        }
        return builder.toString();
    }

    /**
     * Reads the user's command input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public String showTaskMarked(Task task) {
        return "Good job on the completion, my minion!\n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task the task that was unmarked
     */
    public String showTaskUnmarked(Task task) {
        return "Better get to work before I execute you!\n" + task.toString();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task the task that was added
     * @param size the current number of tasks in the list
     */
    public String showTaskAdded(Task task, int size) {
        return "Approved. I've added this to your list of duties:\n"
                + task.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that no tasks were found containing the given keyword.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public String showNoTaskFound(String keyword) {
        return "No tasks found containing the keyword: " + keyword;
    }

    /**
     * Displays the list of tasks that match the search criteria.
     *
     * @param tasks The list of tasks that were found to match the search keyword.
     */
    public String showTasksFound(ArrayList<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            builder.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return builder.toString();
    }
}
