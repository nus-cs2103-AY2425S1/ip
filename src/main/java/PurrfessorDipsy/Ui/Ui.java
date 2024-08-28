package PurrfessorDipsy.Ui;

import PurrfessorDipsy.Task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user, including displaying messages, prompts,
 * and reading user input from the console. It provides various methods to display specific types of messages,
 * such as welcome messages, error messages, and task-related messages.
 *
 * This class facilitates communication between the program and the user.
 */
public class Ui {

    /** Scanner to read user input from the input stream (e.g., System.in). */
    private final Scanner in;

    /** PrintStream to output messages to the output stream (e.g., System.out). */
    private final PrintStream out;

    /**
     * Constructs a new {@code Ui} object using the default input and output streams (System.in and System.out).
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new {@code Ui} object with the specified input and output streams.
     * This constructor provides flexibility for testing or redirecting input and output.
     *
     * @param in  The input stream from which user input will be read.
     * @param out The output stream to which messages will be printed.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts the user for input by displaying a message, reads the input, and returns the trimmed input string.
     * This method is used to capture commands from the user.
     *
     * @return The user's input as a trimmed string.
     */
    public String getInput() {
        out.print("Enter command: ");
        return in.nextLine().trim();
    }

    /**
     * Prints a message enclosed within terminal lines for a formatted appearance.
     *
     * @param message The message to print within the terminal lines.
     */
    private void printWithTerminalLines(String message) {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        out.println(TERMINAL_LINE);
        out.println(message);
        out.println(TERMINAL_LINE);
    }

    /**
     * Prints a welcome message when the program starts.
     */
    public void printWelcomeMessage() {
        String introduction = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\n" +
                "How can I purrvide assistance? Purrhaps I could lend a paw!";
        printWithTerminalLines(introduction);
    }

    /**
     * Prints a farewell message when the program exits.
     */
    public void printExitMessage() {
        printWithTerminalLines("Fur-well friend, stay paw-sitive!");
    }

    /**
     * Prints an error message with a specific error description.
     * This method is used to inform the user when an error occurs during the program's execution.
     *
     * @param message The error message to display.
     */
    public void printErrorMessage(String message) {
        printWithTerminalLines("Error: " + message);
    }

    /**
     * Prints a message indicating that a task has been added, along with the total number of tasks in the list.
     * This method confirms to the user that the task has been successfully added.
     *
     * @param task The task that was added.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public void printTaskAddedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("(=ↀωↀ=)ノ Task added!\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    /**
     * Prints a message indicating that a task has been deleted, along with the total number of tasks remaining
     * in the list.
     * This method confirms to the user that the task has been successfully deleted.
     *
     * @param task The task that was deleted.
     * @param numberOfTasks The current number of tasks remaining in the list.
     */
    public void printTaskDeletedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("Purrr, I've swatted this task away:\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     * This method confirms to the user that the task has been successfully marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public void printMarkTaskDoneMessage(Task task) {
        printWithTerminalLines("Meow! I’ve scratched this task off the list!\n" + task);
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     * This method confirms to the user that the task has been marked as incomplete.
     *
     * @param task The task that has been marked as not done.
     */
    public void printMarkTaskUndoneMessage(Task task) {
        printWithTerminalLines("Mrrreow! I’ve batted this task back onto the list.\n" + task);
    }

    /**
     * Prints the list of tasks given as a parameter. If the task list is empty, a special message is displayed
     * to indicate that there are no tasks.
     * Otherwise, each task is listed with its corresponding index.
     *
     * @param tasks The list of tasks to display.
     */
    public void printListOfTasks(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            printWithTerminalLines("Your task list is as empty as a well-sunned nap spot.");
        } else {
            StringBuilder result = new StringBuilder("Time to stretch those paws and tackle your tasks!\n");
            for (int i = 0; i < taskCount; i++) {
                int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
                result.append(printedIndex).append(".").append(tasks.get(i));
                if (i < taskCount - 1) { // Don't append a newline after the last task
                    result.append("\n");
                }
            }
            printWithTerminalLines(result.toString());
        }
    }
}
