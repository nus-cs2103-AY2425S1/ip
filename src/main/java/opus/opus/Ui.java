package opus;

import java.util.Scanner;

/**
 * Handles interactions with the user, including displaying messages
 * and reading input commands.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes a Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Opus");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
