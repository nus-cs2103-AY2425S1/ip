package chatsy;

import java.util.Scanner;

/**
 * Handles user interactions in the Chatsy application.
 */
public class UI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";

    /**
     * Greets the user with a welcome message.
     *
     * @param name The name of the application to be used in the greeting.
     */
    public void greet(String name) {
        output("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    /**
     * Displays the given message to the user with formatting.
     *
     * @param message The message to be displayed.
     */
    public void output(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Reads the next command from the user input.
     *
     * @return The user's command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
