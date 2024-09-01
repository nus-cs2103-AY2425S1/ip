package mummy.ui;

import java.util.Scanner;


/**
 * The `Ui` class represents the user interface of the application.
 * It provides methods for displaying messages, reading user input, and showing error messages.
 * The class also contains a logo and a scanner object for reading user input from the console.
 */
public class Ui {

    private static final String DIVIDER = "-".repeat(100);

    private final String logo;

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new Ui object with the specified logo.
     *
     * @param logo the logo to be displayed
     */
    public Ui(String logo) {
        this.logo = logo;
    }

    /**
     * Displays a welcome message to the user.
     * The welcome message includes the logo and a greeting.
     */
    public void showWelcome() {
        show("Hello from\n"
                + logo + "\n"
                + "What can I do for you?\n");
    }

    /**
     * Reads the next command from the user.
     * 
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Closes the scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays the given message on the console.
     *
     * @param message the message to be displayed
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("OOPS!!!! " + message);
    }

    /**
     * Displays a line divider in the console.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }
}
