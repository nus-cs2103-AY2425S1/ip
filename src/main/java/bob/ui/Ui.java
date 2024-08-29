package bob.ui;

import java.util.Scanner;

/**
 * Class for user interface.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a new user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public static void showWelcome() {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a command from the user.
     *
     * @return String The command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public static void showError(String message) {
        System.out.println(message);
    }
}
