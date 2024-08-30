package ratchet.ui;

import java.util.Scanner;

/**
 * Class to handle interaction with the user.
 */
public class Ui {
    private static final String INDENT = "    ";
    private final Scanner scanner;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from command line.
     *
     * @return User input string.
     */
    public String read() {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            return input;
        }
        return "bye";
    }

    /**
     * Greets the user when bot first starts.
     */
    public void greet() {
        printLine();
        printWithIndent("Hello! I'm Ratchet\n" + INDENT + "What can I do for you?");
        printLine();
    }

    /**
     * Farewells the user when user exits.
     */
    public void exit() {
        printLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints an indented error message with dividers
     *
     * @param message The error message to be printed.
     */
    public void error(String message) {
        printLine();
        printWithIndent(message);
        printLine();
    }

    /**
     * Prints a line to be used as divider by the bot
     */
    public void printLine() {
        System.out.println("   ________________________________________________________");
    }

    /**
     * Prints a line with an indent.
     *
     * @param message The message to be indented.
     */
    public void printWithIndent(String message) {
        System.out.println(INDENT + message);
    }
}
