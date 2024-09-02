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
     * Displays a welcome message.
     */
    public void welcome() {
        System.out.println("Hello! I'm Light\nWhat can I do for you?");
        lineBreak();
    }

    /**
     * Displays a goodbye message.
     */
    private static void lineBreak(){
        String lineBreak = "____________________________________________________________\n";
        System.out.println(lineBreak);
    }

    /**
     * Displays an error message.
     * @param e The exception containing the error message to be displayed.
     */
    public void showError(LightException e) {
        System.out.println(e.toString());
    }

    /**
     * Displays a message.
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        lineBreak();
        System.out.println(message);
        lineBreak();
    }

    /**
     * Reads the command entered by the user.
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the user interface.
     */
    public void closeUI() {
        scanner.close();
    }
}
