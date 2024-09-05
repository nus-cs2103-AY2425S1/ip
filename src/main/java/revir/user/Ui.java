package revir.user;

import java.util.Scanner;

/**
 * The Ui class represents the user interface of the application.
 * It provides methods for displaying messages, reading user input, and handling errors.
 */
public class Ui {
    private String name;
    private Scanner scanner;

    /**
     * Constructs a new Ui object with the given name.
     *
     * @param name the name of the user interface
     */
    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        this.showLine();
    }

    /**
     * Displays a line separator.
     */
    private void showLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Displays an error message.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        System.err.println(error);
    }

    /**
     * Displays an exit message.
     */
    public void showExit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Displays the result of an operation.
     *
     * @param result the result to display
     */
    public void showResult(String result) {
        this.showLine();
        System.out.println(result);
        this.showLine();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the next line of input
     */
    public String readInput() {
        String nextLine = this.scanner.nextLine();
        return nextLine;
    }

    /**
     * Checks if the scanner is open.
     *
     * @return true if the scanner is open, false otherwise
     */
    public boolean isOpen() {
        return this.scanner.hasNext();
    }
}
