package components;

import exceptions.LightException;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a line break.
     */
    private static String lineBreak() {
        String lineBreak = "____________________________________________________________\n";
        return lineBreak;
    }

    /**
     * Displays a welcome message.
     */
    public String welcome() {
        return "Hello! I'm Light\nWhat can I do for you?" + lineBreak();
    }

    /**
     * Displays an error message.
     *
     * @param e The exception containing the error message to be displayed.
     */
    public String showError(LightException e) {
        return e.toString();
    }

    /**
     * Displays a message.
     *
     * @param message The message to be displayed.
     */
    public String beautifyMessage(String message) {
        return message;
    }

    /**
     * Reads the command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the user interface.
     */
    public void closeUi() {
        scanner.close();
    }
}
