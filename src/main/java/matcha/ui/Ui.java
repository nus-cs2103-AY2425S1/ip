package matcha.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the Matcha program.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a UI with a scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return Returns user input as a string.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the UI scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Prints a divider line.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        printDivider();
        System.out.println("Hi there! I am Matcha, your personal chatbot.");
        System.out.println("How can I help you today?");
        printDivider();
    }

    /**
     * Says goodbye to the user.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Shows an error message when loading the file.
     */
    public void showLoadingError() {
        printDivider();
        System.out.println("There was an error loading the file. Generating a new task list.");
        printDivider();
    }
}
