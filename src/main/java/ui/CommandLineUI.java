package ui;

import java.util.Scanner;

/**
 * Provides a command-line interface (CLI) for interacting with the user.
 * It supports displaying messages, reading user input, and formatting output.
 */
public class CommandLineUi {

    /** Scanner for reading user input from the command line. */
    private Scanner sc;

    /**
     * Constructs a CommandLineUI and initializes the scanner for reading input.
     */
    public CommandLineUi() {
        // Initialize scanner
        sc = new Scanner(System.in);
    }

    /**
     * Displays a divider line in the CLI to mark the start/end of a bot speach.
     */
    public void showDivider() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Greets the user with a welcome message and displays a divider.
     */
    public void greet() {
        showDivider();
        speakLine("Bone-jaw! I'm Oui Oui Baguette.");
        speakLine("What can I do for you? Oui Oui");
        showDivider();
    }

    /**
     * Bids farewell to the user with a goodbye message and displays a divider.
     */
    public void farewell() {
        showDivider();
        speakLine("Bye. Hope to see you soon! Oui Oui");
        showDivider();
    }

    /**
     * Reads the next line of user input from the command line.
     *
     * @return The input entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a single message formatted for a CLI chat response.
     *
     * @param s The response message to be printed.
     */
    public void speakLine(String s) {
        speakLines(new String[]{s});
    }

    /**
     * Prints multiple messages formatted for a CLI chat response.
     *
     * @param strs The list of response messages to be printed.
     */
    public void speakLines(String[] strs) {
        for (String s : strs) {
            System.out.println("\t " + s);
        }
    }
}
