package bob.ui;

import java.util.Scanner;

/**
 * Class to represent interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message to the user.
     */
    public static String showWelcome() {
        String logo = "Bob";
        return "Hello! I'm " + logo + "\n" + "What can I do for you?";
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
