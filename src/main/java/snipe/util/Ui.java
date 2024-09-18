package snipe.util;

import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user. It is responsible for displaying messages,
 * reading user input, and providing a user-friendly interface for the application.
 */
public class Ui {
    /** The name of the application. */
    private static final String NAME = "Snipe";

    /** The ASCII art logo for the application. */
    private static final String LOGO
            = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";

    private static final int LINE_LENGTH = 60;

    /** A horizontal line used for formatting output. */
    private static final String HORIZONTAL_LINE = "_".repeat(LINE_LENGTH);
    public static final String WELCOME_MESSAGE = "Hello! I'm\n" + NAME + "\nWhat can I do for you?";

    /** The {@link Scanner} object for reading user input. */
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} object to handle user interactions.
     * Initializes the {@link Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user, including the application's logo.
     */
    public void showWelcome() {
        printWithLines(WELCOME_MESSAGE);
    }

    /**
     * Displays an error message when loading tasks from the file fails.
     */
    public void showLoadingError() {
        printWithLines("Error loading tasks from file.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printWithLines(message);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The user input as a {@link String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message between two horizontal lines for better readability.
     *
     * @param message The message to be printed.
     */
    public void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public String returnResponse(String message) {
        return message;
    }

    /**
     * Closes the {@link Scanner} used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
