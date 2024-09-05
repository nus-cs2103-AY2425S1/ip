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
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\nWhat can I do for you?";

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

    /**
     * Prompts the user to select a type of task to add, providing numbered options for Todo, Deadline, or Event tasks.
     *
     * @return the number corresponding to the task type chosen by the user as a String.
     */
    public String getTaskType() {
        out.println("What kind of task would you like to add today?\n"
                + "  1: Todo (Just a simple task meow)\n"
                + "  2: Deadline (Something with a time limit meow)\n"
                + "  3: Event (A task with a start and end time meow)\n"
                + "Please enter the number of the task type you'd like to add meow~");
        return in.nextLine().trim();
    }

    /**
     * Prompts the user to enter the name of a task.
     *
     * @return the task name entered by the user as a String.
     */
    public String getTaskName() {
        out.println("What will this task be called meow?");
        return in.nextLine().trim();
    }

    /**
     * Prompts the user to enter a date and time in the 'yyyy-MM-ddTHH:mm' format and parses it.
     * If the input format is incorrect, it keeps prompting the user until a valid format is entered.
     *
     * @param prompt the message to display asking for the date and time.
     * @return the parsed LocalDateTime object.
     */
    public LocalDateTime getDateTime(String prompt) {
        out.println(prompt
                + " (e.g., 2024-01-01T13:00 for January 1, 2024, at 1:00 PM)");
        while (true) {
            String input = in.nextLine().trim();
            try {
                return Parser.parseTime(input);
            } catch (DateTimeParseException e) {
                out.println("Meow /ᐠ > ˕ <マ Invalid date/time format ! "
                        + "Please use 'yyyy-MM-ddTHH:mm' format.");
            }
        }
    }
}
