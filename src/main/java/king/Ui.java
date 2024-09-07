package king;

import java.util.ArrayList;
import java.util.Scanner;

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
    public void showWelcome() {
        System.out.println("Behold the wrath of the\n" + LOGO);
        showLine();
        System.out.println("What do you have for me?");
        showLine();
    }

    /**
     * Displays the goodbye message when the application is exiting.
     */
    public void showGoodbye() {
        System.out.println("You are dismissed, my humble servant.");
        showLine();
    }

    /**
     * Displays a horizontal line to visually separate sections of the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays the current list of tasks.
     *
     * @param taskList the list of tasks to be displayed
     */
    public void showTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.getTask(i).toString());
        }
        showLine();
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
    public void showTaskMarked(Task task) {
        System.out.println("Good job on the completion, my minion!\n" + task.toString());
        showLine();
    }

    /**
     * Displays a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Better get to work before I execute you!\n" + task.toString());
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task the task that was added
     * @param size the current number of tasks in the list
     */
    public void showTaskAdded(Task task, int size) {
        showLine();
        System.out.println("Approved. I've added this to your list of duties:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message indicating that no tasks were found containing the given keyword.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public void showNoTaskFound(String keyword) {
        System.out.println("No tasks found containing the keyword: " + keyword);
        showLine();
    }

    /**
     * Displays the list of tasks that match the search criteria.
     *
     * @param tasks The list of tasks that were found to match the search keyword.
     */
    public void showTasksFound(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        showLine();
    }
}
