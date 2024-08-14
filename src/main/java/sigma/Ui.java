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
    private Scanner scanner;
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO =
            "   _____ _                       \n"
                    + "  / ____(_)                      \n"
                    + " | (___  _  __ _ _ __ ___   __ _ \n"
                    + "  \\___ \\| |/ _` | '_ ` _ \\ / _` |\n"
                    + "  ____) | | (_| | | | | | | (_| |\n"
                    + " |_____/|_|\\__, |_| |_| |_|\\__,_|\n"
                    + "            __/ |                \n"
                    + "           |___/                 \n";


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
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Greetings, I'm Sigma!");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message when a {@code SigmaException} is thrown.
     *
     * @param e The exception containing the error message.
     */
    public void showErrorMessage(SigmaException e) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(e);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message when the application is about to exit.
     */
    public void showGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Catch ya on the flip side, my dude! See ya soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showList(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i + 1));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public void showAddedTask(Task task, int numberOfTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param task The task that has been removed.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public void showDeletedTask(Task task, int numberOfTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
}
