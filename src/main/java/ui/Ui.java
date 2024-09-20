package ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    /**
     * Constructs a new Ui object to manage user interactions.
     * Initializes a Scanner object to read user input from the console.
     */
    private final Scanner scanner;

    /**
     * Constructs a new Ui object and initializes a Scanner object to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads command entered by the user.
     *
     * @return user's command as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a formatted line to separate different parts.
     */
    public void showLine() {
        System.out.println("___________________________________________________________________________");
    }

    /**
     * Displays a welcome message.
     */
    public void welcomeUser() {
        System.out.println("Hello! I'm Handsome\nWhat can I do for you?\n");
    }

    /**
     * Displays a goodbye message.
     */
    public void showBye() {
        System.out.println("Bye, it was an amazing conversation!");
    }

    /**
     * Displays message.
     *
     * @param message The desired message to be displayed.
     */
    public static void show(String message) {
        assert message != null : "Well, I can't exactly show nothing can I?";
        System.out.println(message);
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
