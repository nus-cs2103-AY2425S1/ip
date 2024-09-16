package jade.ui;

import java.util.Scanner;

import jade.parser.Parser;

/**
 * Handles user interaction and input for the Jade application.
 */
public class Ui {
    public static final String INDENT = " ".repeat(5);
    public static final String TOP_LINE = " ".repeat(4) + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + " ".repeat(4) + "_".repeat(60);

    private final Parser parser;
    private final Scanner scanner;

    /**
     * Constructs a Ui object with the specified TaskManager and Parser.
     * Initialises the scanner for reading user input.
     *
     * @param parser The Parser to interpret user commands.
     */
    public Ui(Parser parser) {
        this.parser = parser;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interface and handles user commands.
     */
    public void run() {
        parser.parse(scanner);
    }

    /**
     * Displays a formatted message for the text-based UI
     * with indentation and a top and bottom line for decoration.
     *
     * @param message The message to display for the text-based UI.
     * @return A formatted string with the message encapsulated by a top and bottom line.
     */
    public static String formatTextUiMessage(String message) {
        return TOP_LINE + INDENT + message + BOT_LINE;
    }
}
