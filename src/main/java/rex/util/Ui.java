package rex.util;

import java.util.Scanner;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Task;

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
    public static String getGreeting() {
        System.out.println("Hello! I'm Rex! " + RAWR);
        System.out.println("What can I do for you? " + RAWR);
        return "Hello! I'm Rex! " + RAWR + "\nWhat can I do for you? " + RAWR;
    }

    /**
     * Displays a help message listing available commands.
     */
    public String getHelp() {
        return Command.getCommandList();
    }

    /**
     * Displays the current list of tasks.
     *
     * @param output The list string to be displayed.
     */
    public String displayList(String output) {
        return output;
    }

    /**
     * Displays the output for the tasks that match the keyword searched.
     *
     * @param output A formatted string containing the list of tasks that match the search criteria.
     *               This string is printed as is to the console.
     */
    public String findTask(String output) {
        return output;
    }

    /**
     * Informs the user that a new task has been added.
     *
     * @param task The {@code Task} that was added to the list.
     */
    public String addTask(Task task) {
        String output = "Got it. I've added this task:\n";
        output += "  " + task;
        output += "\nNow you have " + Task.getNumberOfTasks() + " tasks in the list.";
        return output;
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The {@code Task} that was marked as done.
     */
    public String markTask(Task task) {
        String output = "Nice! I've marked this task as done:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The {@code Task} that was marked as not done.
     */
    public String unmarkTask(Task task) {
        String output = "OK, I've marked this task as not done yet:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param task The {@code Task} that was removed from the list.
     */
    public String deleteTask(Task task) {
        String output = "Noted. I've removed this task:";
        output += "  " + task;
        output += "Now you have " + Task.getNumberOfTasks() + " tasks in the list.";
        return output;
    }

    /**
     * Prints a playful "rawr" message to the user.
     */
    public String rawr() {
        return RAWR + "!";
    }

    /**
     * Displays a goodbye message and closes the {@code Scanner}.
     */
    public String getGoodbye() {
        scanner.close();
        return "Bye. Hope to see you again soon! " + RAWR;
    }

    /**
     * Displays an error message to the user with a default error prefix.
     *
     * @param e The {@code InvalidInputException} containing the error message.
     */
    public String errorMessage(InvalidInputException e) {
        return errorMessage(e.getMessage());
    }

    /**
     * Displays a custom error message to the user with a default error prefix.
     *
     * @param message The error message to be displayed.
     */
    public String errorMessage(String message) {
        return ERROR_PREFIX + " " + message;
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    private void printDivider() {
        System.out.println(DIVIDER);
    }
}
