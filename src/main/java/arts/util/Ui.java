package arts.util;

import java.util.Scanner;

/**
 * Represents the Ui class that handles interactions with the user, including displaying messages,
 * reading user input, and showing error messages. It provides a simple interface
 * for user communication.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        String logo = "     _    _____  _______  _____  \n"
                + "    / \\  |  __ \\|__   __|/ ____| \n"
                + "   / _ \\ | |__) |  | |  | (___   \n"
                + "  / ___ \\|  _  /   | |   \\___ \\  \n"
                + " /_/   \\_\\_| \\_\\   |_|   |_____/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Arts, your go-to Chatbot.");
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a command from the user input.
     *
     * @return A trimmed string representing the user's command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a line separator to the user.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays a message to the user, enclosed by line separators.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}
