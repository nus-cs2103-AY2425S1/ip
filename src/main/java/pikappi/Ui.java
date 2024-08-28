package pikappi;

import java.util.Scanner;

/**
 * Represents a user interface that interacts with the user.
 */
public class Ui {
    static Scanner reader = new Scanner(System.in);

    /**
     * Returns a Ui object that interacts with the user.
     */
    public Ui() {
    }

    /**
     * Greets the user when the program starts.
     */
    public void greet() {
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
    }

    /**
     * Bids farewell to the user when the program ends.
     */
    public void goodbye() {
        System.out.println("Pi-kapi! See you again~\n");
    }

    /**
     * Shows a horizontal line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user input from the command line.
     *
     * @return User input from the command line
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Shows an error message to the user.
     *
     * @param message Error message to be shown to the user
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }
}
