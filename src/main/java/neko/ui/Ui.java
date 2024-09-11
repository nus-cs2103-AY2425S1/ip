package neko.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import neko.Parser;

/**
 * The Ui class handles all interactions with the user and works as the user interface
 * for interacting with Neko, providing a friendly cat-like persona for the user.
 */
public class Ui {

    /** Divider line used to separate messages for clarity. */
    private static final String DIVIDER = "===================================================";

    /** Greeting message displayed when the program starts. */
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\n"
            + "What can I do for you today?\n"
            + "Type 'help' to see a list of commands you can use meow!";

    /** Exit message displayed when the program ends. */
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon meow ฅ ฅ";

    /** Scanner for reading user input from the command line. */
    private static final Scanner scanner = new Scanner(System.in);

    /** Scanner object for reading user input. */
    private final Scanner in;

    /** PrintStream object for printing messages to the console. */
    private final PrintStream out;

    /**
     * Default constructor that initializes the Ui with System.in and System.out.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor that accepts custom input and output streams, primarily for testing purposes.
     *
     * @param in  the InputStream to be used for reading user input.
     * @param out the PrintStream to be used for outputting messages.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts the user to enter a command and reads the input.
     *
     * @return the user command as a String.
     */
    public String getUserCommand() {
        out.println("Enter command:");
        return in.nextLine();
    }

    public String getGreetingMessage() {
        return GREETING_MESSAGE;
    }

    /**
     * Displays a message to the user followed by a divider for clarity.
     *
     * @param message the message to be displayed.
     */
    public void showMessage(String message) {
        out.println(message + "\n" + DIVIDER);
    }

    /**
     * Displays a message to the user without the divider.
     *
     * @param message the message to be displayed.
     */
    public void showMessageWithoutDivider(String message) {
        out.println(message);
    }

    /**
     * Displays the greeting message when the program starts.
     */
    public void showGreeting() {
        showMessage(GREETING_MESSAGE);
    }

    /**
     * Displays the exit message when the program ends.
     */
    public void showExitMessage() {
        showMessage(EXIT_MESSAGE);
    }
}
