package rex.util;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Task;

import java.util.Scanner;

/**
 * The {@code Ui} class handles the user interface of the application.
 * It provides methods to interact with the user by reading user inputs,
 * displaying messages, prompts, and error messages.
 */
public class Ui {
    /**
     * Constructs a new {@code Ui} instance and initializes the {@code Scanner}.
     */
    // Horizontal line divider;
    private static final String DIVIDER = "____________________________________________________________";

    // "rawr" string added to end of each print statement
    private static final String RAWR = "rawr";

    // "RAWRRRR" that comes with each error message
    private static final String ERROR_PREFIX = "RAWRRRR!!!";

    // Scanner placeholder
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a {@code String}.
     */
    public String readInput() {
        printDivider();
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        printDivider();
        System.out.println("Hello! I'm Rex! " + RAWR);
        System.out.println("What can I do for you? " + RAWR);
    }

    /**
     * Displays a help message listing available commands.
     */
    public void help() {
        printDivider();
        System.out.println(Command.getCommandList());
    }

    /**
     * Displays the current list of tasks.
     *
     * @param output The list string to be displayed.
     */
    public void displayList(String output) {
        printDivider();
        System.out.print(output);
    }


    public void findTask(String output) {
        printDivider();
        System.out.print(output);
    }

    /**
     * Informs the user that a new task has been added.
     *
     * @param task The {@code Task} that was added to the list.
     */
    public void addTask(Task task) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The {@code Task} that was marked as done.
     */
    public void markTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The {@code Task} that was marked as not done.
     */
    public void unmarkTask(Task task) {
        printDivider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param task The {@code Task} that was removed from the list.
     */
    public void deleteTask(Task task) {
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Prints a playful "rawr" message to the user.
     */
    public void rawr() {
        printDivider();
        System.out.println(RAWR + "!");
    }

    /**
     * Displays a goodbye message and closes the {@code Scanner}.
     */
    public void goodbye() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon! " + RAWR);
        printDivider();
        scanner.close();
    }

    /**
     * Displays an error message to the user with a default error prefix.
     *
     * @param e The {@code InvalidInputException} containing the error message.
     */
    public void errorMessage(InvalidInputException e) {
        errorMessage(e.getMessage());
    }

    /**
     * Displays a custom error message to the user with a default error prefix.
     *
     * @param message The error message to be displayed.
     */
    public void errorMessage(String message) {
        printDivider();
        System.out.println(ERROR_PREFIX + " " + message);
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    private void printDivider() {
        System.out.println(DIVIDER);
    }
}
