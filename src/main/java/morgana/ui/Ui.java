package morgana.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles user interaction, providing methods for reading user input and
 * displaying messages.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a {@code Ui} object using the standard input and output streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a {@code Ui} object with the specified input and output streams.
     *
     * @param in The input stream for reading user input.
     * @param out The output stream for displaying messages.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Displays a welcome message.
     *
     * @param name The name of the application.
     */
    public void showWelcomeMessage(String name) {
        showToUser("Hello! I'm %s.".formatted(name), "What can I do for you?");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbyeMessage() {
        showToUser("Bye! Hope to see you again soon!");
    }

    /**
     * Displays one or more messages framed by horizontal lines.
     *
     * @param messages The messages to be displayed.
     */
    public void showToUser(String... messages) {
        out.print(HORIZONTAL_LINE);
        for (String message : messages) {
            out.println(message);
        }
        out.println(HORIZONTAL_LINE);
    }
}
