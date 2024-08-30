package repsmax;

import java.util.Scanner;
import java.util.List;

/**
 * Handles all user interactions in the application.
 * <p>
 * The {@code Ui} class is responsible for managing user input and output.
 * It provides methods for reading user commands, displaying messages,
 * and showing various informational prompts.
 * </p>
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a {@code Ui} object and initializes the scanner for reading
     * user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the input line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     * <p>
     * The message includes a greeting and a prompt asking what the user
     * would like to do.
     * </p>
     */
    public void showWelcome() {
        System.out.println("  ____________________________________________________________");
        System.out.println("   Hello! I'm Repsmax");
        System.out.println("   What can I do for you?");
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     * <p>
     * The message bids farewell and expresses hope to see the user again
     * soon.
     * </p>
     */
    public void showGoodbye() {
        System.out.println("  ____________________________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Displays an error message when loading tasks fails.
     * <p>
     * The message indicates that an error occurred while loading tasks
     * from storage.
     * </p>
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks.");
    }

    /**
     * Displays a custom error message.
     *
     * @param message the error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a horizontal line for visual separation.
     * <p>
     * The line is used to enhance readability in the user interface output.
     * </p>
     */
    public void showLine() {
        System.out.println("____________________________");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message the message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the user for a search keyword and displays the results.
     */
    public void showSearchResults(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No tasks found with the given keyword.");
        } else {
            showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                showMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
