package citadel.ui;

import java.util.Scanner;
import citadel.Task.TaskList;
import citadel.Task.Task;
import citadel.exception.CitadelException;

/**
 * The {@code TextUI} class is responsible for managing all user interactions in the Citadel application.
 * It provides methods to display messages, handle input, and print task-related information to the console.
 */
public class TextUI {

    /** The name of the Citadel application. */
    private static final String NAME = "citadel";

    /** The introductory message displayed when the application starts. */
    private static final String INTRO = "Hello! I'm " + NAME + "\n";

    /** The prompt message asking what the user wants to do. */
    private static final String QUESTION = "What can I do for you?\n";

    /** The farewell message displayed when the application exits. */
    private static final String GOODBYE = "Bye. Hope to see you again soon!\n";

    /** The {@code Scanner} object used to read user input from the console. */
    private final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructs a new {@code TextUI} object.
     */
    public TextUI() {
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of user input as a {@code String}.
     */
    public String nextLine() {
        return this.SCANNER.nextLine();
    }

    /**
     * Prints the start message when the application begins.
     */
    public void printStart() {
        String start = INTRO + QUESTION;
        System.out.println(start);
    }

    /**
     * Prints the list of tasks stored in the application.
     *
     * @param items The {@link TaskList} containing the tasks to be printed.
     */
    public void printTasks(TaskList items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).printTask());
        }
    }

    /**
     * Prints the number of tasks currently stored in the application.
     *
     * @param items The {@link TaskList} whose size is to be printed.
     */
    public void printTaskCount(TaskList items) {
        System.out.println("Now you have " + items.size()
                + " tasks in the list");
    }

    /**
     * Prints a {@link CitadelException} message to the console.
     *
     * @param e The {@link CitadelException} to be printed.
     */
    public void printCitadelException(CitadelException e) {
        System.out.println(e.toString());
    }

    /**
     * Prints an error message for date parsing errors.
     */
    public void printDateTimeParseException() {
        System.out.println("Incorrect Date Format! Please write the date "
                + "in this format: dd/MM/yyyy HH:mm!");
    }

    /**
     * Prints a generic exception message to the console.
     *
     * @param e The {@link Exception} to be printed.
     */
    public void printException(Exception e) {
        System.out.println("Error occurred: "
                + e.getMessage());
    }

    /**
     * Prints the farewell message when the application exits.
     */
    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints a confirmation message after adding a task to the task list.
     *
     * @param t The {@link Task} that was added.
     * @param items The {@link TaskList} to which the task was added.
     */
    public static void printTask(Task t, TaskList items) {
        System.out.println("Got it! I have added: " + t);
        System.out.println("Now you have " + items.size()
                + " tasks in the list");
    }

    /**
     * Prints a confirmation message after marking a task as done.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param index The index of the task that was marked as done.
     */
    public static void printMark(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
    }

    /**
     * Prints a confirmation message after unmarking a task as not done.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param index The index of the task that was unmarked.
     */
    public static void printUnmark(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
    }

    /**
     * Prints a confirmation message after deleting a task from the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     * @param t The {@link Task} that was deleted.
     */
    public static void printDelete(TaskList tasks, Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + tasks.size()
                + " tasks in the list");
    }

    /**
     * Prints the tasks that match a given keyword search.
     *
     * @param tasks The {@link TaskList} containing the matching tasks.
     */
    public static void printFind(TaskList tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        new TextUI().printTasks(tasks);
    }
}
