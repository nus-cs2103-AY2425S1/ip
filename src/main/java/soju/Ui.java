package soju;

import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user.
 * It provides methods for displaying messages, reading user input,
 * and handling errors.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays an error message when there is an issue loading tasks from storage.
     */
    public void showLoadingError() {
        // Implementation can be added if needed
    }

    /**
     * Prints a horizontal line to the console.
     * This is used to separate different sections of the output.
     */
    public void printHorizontalLine() {
        System.out.println("-------------------------------------");
    }

    /**
     * Prints the error message associated with a {@code SojuException}.
     * This method also prints a horizontal line for formatting.
     *
     * @param e The {@code SojuException} to print the message from.
     */
    public void printError(SojuException e) {
        System.out.println(e.getMessage());
        printHorizontalLine();
    }

    /**
     * Prints a greeting message to the user when the application starts.
     */
    public void greet() {
        printHorizontalLine();
        String greetingMessage = "Hello! I'm Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
        printHorizontalLine();
    }

    /**
     * Prints an exit message to the user when the application is closing.
     */
    public void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        printHorizontalLine();
    }

    /**
     * Prints a given string to the console.
     *
     * @param string The string to be printed.
     */
    public void printString(String string) {
        System.out.println(string);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
