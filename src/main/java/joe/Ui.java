package joe;

import java.util.ArrayList;
import java.util.Scanner;

import joe.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by
 * providing methods for printing messages and reading user input.
 */
public class Ui {
    /**
     * Constructs a {@code Ui} object to manage user interactions.
     */
    public Ui() {
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcome() {
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
    }

    /**
     * Prints a divider line to separate sections in the console output.
     */
    public void printDivider() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints an exit message to the user.
     */
    public void printExit() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message to the user.
     *
     * @param msg The error message to be printed.
     */
    public void printError(String msg) {
        System.out.println("\t" + msg);
    }

    /**
     * Prints an error message indicating a loading error.
     */
    public void printLoadingError() {
        System.out.println("An error occurred while loading data file");
    }

    /**
     * Reads the input from the user via the command line interface.
     *
     * @return Raw string input provided by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints a header followed by a list of tasks in order.
     *
     * @param header The text to be printed before displaying the tasks.
     * @param tasks The list of tasks to be printed.
     */
    public void printResponse(String header, ArrayList<Task> tasks) {
        System.out.println("\t" + header);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String msg = String.format("%d. %s", i + 1, task.toString());
            System.out.println("\t" + msg);
        }
    }

    /**
     * Prints a message indicating a new task has been added along with the updated count of tasks.
     *
     * @param newTask The new task that has been added.
     * @param size The current number of tasks after adding the new task.
     */
    public void printAddedTask(Task newTask, int size) {
        System.out.println("\tGot it. I've added this task:\n\t  " + newTask);
        System.out.printf("\tNow you have %d tasks in the list.%n", size);
    }
}

