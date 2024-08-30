package chatbaby;

import java.util.Scanner;

/**
 * Represents the user interface of the chatbot.
 * This class handles interactions with the user, such as displaying messages,
 * reading commands, and showing errors.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object and initializes the scanner for input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println("Hello! I'm ChatBaby\nWhat can I do for you?");
    }

    /**
     * Prints a separator line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next user command input.
     *
     * @return The trimmed user command as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays the given error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a farewell message to the user.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
