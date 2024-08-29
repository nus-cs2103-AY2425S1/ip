package rex.util;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Task;
import rex.task.TaskList;

import java.util.Scanner;

/**
 * The {@code Ui} class handles the user interface of the application.
 * It provides methods to interact with the user by reading user inputs,
 * displaying messages, prompts, and error messages.
 */
public class Ui {
    private static String separation = "____________________________________________________________";
    private static String rawr = "rawr"; // String appended to end of each print statement for a whimsical touch
    private static String errorPrefix = "RAWRRRR!!!"; // Prefix for error messages
    private Scanner scanner; // Scanner for reading user input

    /**
     * Constructs a new {@code Ui} instance and initializes the {@code Scanner}.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a {@code String}.
     */
    public String readInput() {
        divider();
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        divider();
        System.out.println("Hello! I'm Rex! " + rawr);
        System.out.println("What can I do for you? " + rawr);
    }

    /**
     * Displays a help message listing available commands.
     */
    public void help() {
        divider();
        System.out.println(Command.getCommandList());
    }

    /**
     * Displays the current list of tasks.
     *
     * @param list The {@code TaskList} containing the tasks to be displayed.
     */
    public void displayList(TaskList list) {
        divider();
        System.out.print(list.getListDisplay());
    }

    /**
     * Informs the user that a new task has been added.
     *
     * @param task The {@code Task} that was added to the list.
     */
    public void addTask(Task task) {
        divider();
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
        divider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The {@code Task} that was marked as not done.
     */
    public void unmarkTask(Task task) {
        divider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param task The {@code Task} that was removed from the list.
     */
    public void deleteTask(Task task) {
        divider();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Prints a playful "rawr" message to the user.
     */
    public void rawr() {
        divider();
        System.out.println(rawr + "!");
    }

    /**
     * Displays a goodbye message and closes the {@code Scanner}.
     */
    public void goodbye() {
        divider();
        System.out.println("Bye. Hope to see you again soon! " + rawr);
        divider();
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
        divider();
        System.out.println(errorPrefix + " " + message);
    }

    /**
     * Prints a horizontal line for visual separation in the UI.
     */
    private void divider() {
        System.out.println(separation);
    }
}
