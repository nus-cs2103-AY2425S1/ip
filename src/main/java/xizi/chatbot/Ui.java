package xizi.chatbot;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * Represents the user interface of the chatbot, handling all interactions with the user.
 */
public class Ui {
    private final Scanner in;
    private PrintStream out;

    /**
     * Creates a new Ui instance with default input and output streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Creates a new Ui instance with specified input and output streams.
     *
     * @param in The InputStream to read user input from.
     * @param out The PrintStream to print messages to.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Allows setting a new output stream.
     *
     * @param out The new PrintStream to use for output.
     */
    public void setOutput(PrintStream out) {
        this.out = out;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        out.println("Hello! I'm Xizi.");
        out.println("What can I do for you?");
    }



    /**
     * Prints an error message to the user.
     *
     * @param message The error message to display.
     */
    public void printErrorMessage(String message) {
        out.println("OOPS!!! " + message);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        out.println("Goodbye! Have a great day!");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The trimmed user input.
     */
    public String readUserInput() {
        try {
            return in.nextLine().trim();
        } catch (Exception e) {
            printErrorMessage("Error reading input. Please try again.");
            return "";
        }
    }

    /**
     * Prints an empty line divider to the output stream, being modified from text UI.
     */
    public void showLine() {
        out.println();
    }

    /**
     * Prints a generic message to the output stream.
     *
     * @param message The message to display.
     */
    public void printMessage(String message) {
        out.println(message);
    }
    /**
     * Displays a help message with command descriptions and examples.
     */
    public void printHelp(String... extra) {
        if (extra.length > 0) {
            out.println(extra);
        }
        out.println("Here are the available commands and their formats:");
        out.println();

        // Display command formats and examples
        printCommand("1. list",
                "- Displays all tasks in your list.");

        printCommand("2. todo <task_description>",
                "- Adds a new 'todo' task.",
                "  Example: todo read a book");

        printCommand("3. deadline <task_description> /by <deadline>",
                "- Adds a new 'deadline' task.",
                "  Example: deadline submit report /by 20/08/2024 1800");

        printCommand("4. event <task_description> /from <start_time> /to <end_time>",
                "- Adds a new 'event' task.",
                "  Example: event project meeting /from 15/08/2024 1400 /to 15/08/2024 1600");

        printCommand("5. mark <task_number>",
                "- Marks the specified task as completed.",
                "  Example: mark 3");

        printCommand("6. unmark <task_number>",
                "- Unmarks the specified task as not completed.",
                "  Example: unmark 3");

        printCommand("7. delete <task_number>",
                "- Deletes the specified task.",
                "  Example: delete 3");

        printCommand("8. bye",
                "- Exits the program.");

        printCommand("9. help",
                "- Displays this help message.");

        printCommand("10. list on <date> <time>",
                "- Displays all tasks scheduled for a specific date and time.",
                "  Example: list on 15/08/2024 1400");

        printCommand("11. find <keyword>",
                "- Displays all tasks that contain such keyword");

    }

    private void printCommand(String command, String description) {
        out.println(command);
        out.println("  " + description);
        out.println();
    }

    private void printCommand(String command, String description, String example) {
        out.println(command);
        out.println("  " + description);
        out.println(example);
        out.println();
    }

}
