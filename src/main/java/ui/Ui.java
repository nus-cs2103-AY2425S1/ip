package ui;

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
     * Reads the next line of user input from the command line.
     *
     * @return the input line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
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
    public void showWelcome() {
        printLine();
        System.out.println("Hello! I'm PandaBot.");
        System.out.println("What can I do for you?");
        System.out.println("Type 'help' if you are unsure of how to get started!");
        printLine();
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     * This is shown after the user issues a command to end the session.
     */
    public void showGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display.
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     * This is typically used for displaying issues such as invalid input or system errors.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
