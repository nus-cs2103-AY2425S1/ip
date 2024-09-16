package eevee;

import java.util.Scanner;

/**
 * Represents the CLI UI for the application.
 */
public class Ui {
    private final String divider = "____________________________________________________________\n";
    private final String greeting = "Hello! I'm eevee.\nWhat can I do for you?\n";
    private final String exit = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    /**
     * Constructs an instance of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting String.
     */
    public String getGreeting() {
        return divider + greeting + divider;
    }

    /**
     * Prints the exit String.
     */
    public String getExit() {
        return exit;
    }

    /**
     * Prints the divider String.
     */
    public String getDivider() {
        return divider;
    }

    /**
     * Prints a given message String.
     *
     * @param message The String to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns the input.
     *
     * @return The user input.
     */
    public String getInput() {
        return scanner.nextLine();
    }
}
