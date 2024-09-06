package pandabot.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user, including reading input, displaying messages, and printing lines.
 * This class is responsible for managing user input and output, including welcome and goodbye messages,
 * error handling, and command line interactions.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a line separator for visual clarity in the console output.
     */
    public void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     * This includes a brief introduction and a prompt to get started.
     */
    public static String showWelcome() {
        return """
                Hello! I'm PandaBot.
                What can I do for you?
                Type 'help' if you are unsure of how to get started!""";
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     * This is shown after the user issues a command to end the session.
     */
    public static String showGoodbye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     * This is typically used for displaying issues such as invalid input or system errors.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
        printLine();
    }
}
