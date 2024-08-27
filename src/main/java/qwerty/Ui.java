package qwerty;

import java.util.Scanner;

/**
 * This class encapsulates a Ui object.
 * A Ui object handles accepting user input and displaying outputs.
 */
public class Ui {

    /** The scanner object used to detect user input */
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the string input typed by the user.
     *
     * @return The user's input string.
     */
    public String readCommand() {
        System.out.println(); // blank line before user input
        return scanner.nextLine();
    }

    /**
     * Prints the given message to the console.
     *
     * @param message String message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the given error message to the console.
     * Adds a line that notifies the user that an error has occurred.
     *
     * @param message String message describing the error.
     */
    public void showError(String message) {
        System.out.println("\nWell done! An error has occurred:\n" + message);
    }

    /**
     * Prints a hardcoded greeting message to the user.
     */
    public void showGreeting() {
        System.out.println("""

                It's your worst buggy nightmare, Qwerty.
                What can I do for you?""");
    }

    /**
     * Prints a hardcoded goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("""
                
                Goodbye, and I'll see you within 3 business days.""");
    }

}
