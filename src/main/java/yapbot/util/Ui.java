package yapbot.util;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    private static final String SEPARATOR = "\n-----------------------------------------------\n";
    private Scanner scanner;

    /**
     * Returns a Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message for YapBot.
     */
    public void welcomeUser() {
        System.out.println(SEPARATOR
                + "Powering up...System booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional."
                + SEPARATOR);
    }

    /**
     * Prints the SEPARATOR string used by YapBot to demarcate output from YapBot.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the error message in YapBot format.
     *
     * @param message Error message to be printed.
     */
    public void printError(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }

    /**
     * Receives input from the user.
     *
     * @return string representation of the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints message in YapBot format.
     *
     * @param output Message to be printed.
     */
    public void printOutput(String output) {
        System.out.println(SEPARATOR + output + SEPARATOR);
    }

    /**
     * Closes the Ui instance.
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Prints the goodbye message.
     */
    public void showShutdownMessage() {
        System.out.println(SEPARATOR + "Shutting down...\nYapBot process terminated." + SEPARATOR);
    }
}
