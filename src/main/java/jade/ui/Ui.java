package jade.ui;

import java.util.Scanner;

import jade.parser.Parser;
import jade.task.TaskManager;

/**
 * Handles user interaction and input for the Jade application.
 */
public class Ui {
    public static final String INDENT = " ".repeat(5);
    public static final String TOP_LINE = " ".repeat(4) + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + " ".repeat(4) + "_".repeat(60);

    private final TaskManager taskManager;
    private final Parser parser;
    private final Scanner scanner;

    /**
     * Constructs a Ui object with the specified TaskManager and Parser.
     * Initialises the scanner for reading user input.
     *
     * @param taskManager The TaskManager to manage tasks.
     * @param parser The Parser to interpret user commands.
     */
    public Ui(TaskManager taskManager, Parser parser) {
        this.taskManager = taskManager;
        this.parser = parser;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interface and handles user commands.
     */
    public void run() {
        parser.parse(scanner, taskManager);
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
