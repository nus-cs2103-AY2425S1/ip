package taskalyn;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Manages the user interactions.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs the Ui object to handle user interactions.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the given input within two horizontal lines.
     *
     * @param input Any message from the bot.
     */
    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the starting welcome message from Taskalyn.
     */
    public void printWelcome() {
        printLines("Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?");
    }

    /**
     * Reads and returns the user input.
     *
     * @return User input.
     */
    public String readCommand() {
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            return "unknown command";
        }
    }

    /**
     * Closes the scanner when Taskalyn is stopped.
     */
    public void close() {
        scanner.close();
    }
}
