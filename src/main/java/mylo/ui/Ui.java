package mylo.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the Mylo application.
 * <p></p>
 * <p>This class is responsible for handling user interactions,
 * including displaying messages and reading user input.</p>
 *
 * @author cweijin
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final String name = "Mylo";

    /**
     * Prints a separator line for better readability in the UI.
     */
    public void separator() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints the goodbye message and terminates the program.
     * This method is called when the user decides to exit the application.
     */
    public void exit() {
        String goodbye = "Goodbye. Have a nice day ahead!";
        System.out.println(goodbye);
    }

    /**
     * Prints a welcome message to the user when the application starts.
     * This method greets the user and prompts for further actions.
     */
    public void showWelcomeMessage() {
        String greet = "Hello! Thanks for using " + name + ".";
        String openingQuery = "What can I help you?";

        System.out.println(greet);
        System.out.println(openingQuery);
    }

    /**
     * Displays the specified message to the user.
     *
     * @param message The message to be displayed.
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command entered by the user as a {@code String}.
     *         If the input is blank, it returns "bye", indicating the user's intention to exit.
     */
    public String readCommand() {
        String input = scanner.nextLine();

        if (input.isBlank()) {
            return "bye";
        }

        return input;
    }
}
