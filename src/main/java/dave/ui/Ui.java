package dave.ui;

import java.util.Scanner;

/**
 * Handles user interaction, input, and output in the console for the Dave program.
 */
public class Ui {

    /**
     * Represents the logo for the Dave application.
     */
    private static final String LOGO = " ____    _    __     ______\n"
            + "|  _ \\  / \\   \\ \\   / / ___|\n"
            + "| | | |/ _ \\   \\ \\ / /|  _|\n"
            + "| |_| / ___ \\   \\ V / | |___\n"
            + "|____/_/   \\_\\   \\_/  |_____|\n";
    private final Scanner scanner;
    /**
     * Constructs a new {@code Ui} object with a scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and the logo of the application.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a horizontal line as a separator in the console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message in the console.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads and returns the user's input command from the console.
     *
     * @return The input string from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
