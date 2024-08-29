package james;

import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and reading input.
 */
class Ui {
    private final Scanner SCANNER;

    /**
     * Initializes the Ui class with a new Scanner for reading input.
     */
    public Ui() {
        SCANNER = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println("Hello Big Boy! I'm James\nHow can I assist you today?\n");
    }

    /**
     * Displays an exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("Goodbye. Come back anytime!\n");
    }

    /**
     * Reads a command from the user input.
     * @return The command entered by the user.
     */
    public String readCommand() {
        System.out.print("> ");
        return SCANNER.nextLine();
    }

    /**
     * Displays a message to the user.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the Scanner used for reading user input.
     */
    public void close() {
        SCANNER.close();
    }
}
