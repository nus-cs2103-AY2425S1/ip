package sigma;

import java.util.Scanner;

import sigma.exception.SigmaException;
import sigma.task.Task;
import sigma.task.TaskList;


/**
 * The {@code Ui} class handles all interactions with the user.
 * It is responsible for displaying messages, receiving user input, and
 * showing information related to tasks.
 */
public class Ui {
    private static final String LOGO =
            "   _____ _                       \n"
                    + "  / ____(_)                      \n"
                    + " | (___  _  __ _ _ __ ___   __ _ \n"
                    + "  \\___ \\| |/ _` | '_ ` _ \\ / _` |\n"
                    + "  ____) | | (_| | | | | | | (_| |\n"
                    + " |_____/|_|\\__, |_| |_| |_|\\__,_|\n"
                    + "            __/ |                \n"
                    + "           |___/                 \n";
    private Scanner scanner;


    /**
     * Constructs a {@code Ui} object and initializes the {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the user's input after trimming any leading or trailing whitespace.
     *
     * @return The trimmed input from the user.
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Closes the {@code Scanner} to free up resources.
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcome() {
        return "Greetings, I'm Sigma!\n" + LOGO + "\nWhat can I do for you?";
    }

    /**
     * Displays an error message when a {@code SigmaException} is thrown.
     *
     * @param e The exception containing the error message.
     */
    public String showErrorMessage(SigmaException e) {
        return e.toString();
    }

    /**
     * Displays a goodbye message when the application is about to exit.
     */
    public String showGoodbye() {
        return "Catch ya on the flip side, my dude! See ya soon!";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There are no tasks in your list.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i + 1)));
        }
        return "Here are the tasks in your list:\n" + sb;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + String.format("  %s", task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + String.format("  %s", task);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public String showAddedTask(Task task, int numberOfTasks) {
        return "Got it. I've added this task:\n" + String.format("  %s\n", task)
            + "Now you have " + numberOfTasks + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param task The task that has been removed.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public String showDeletedTask(Task task, int numberOfTasks) {
        return "Noted. I've removed this task:\n" + String.format("  %s\n", task)
                + "Now you have " + numberOfTasks + " tasks in the list.";
    }

    /**
     * Displays the tasks that match a search query.
     *
     * @param tasks The list of matching tasks.
     */
    public String showSearchedTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "  No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i + 1)));
        }

        return "Here are the matching tasks in your list:\n" + sb;
    }
}
