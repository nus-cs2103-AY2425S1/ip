package elsa.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 * Handles interactions with the user, including displaying messages and receiving user input.
 * The class makes use of a Scanner object to read commands from the user via the console.
 *
 * @author Aaron
 */
public class Ui {
    /**
     * Scanner object to read user input from the console.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new elsa.ui.Ui object.
     */
    public Ui() {
    }

    /**
     * Greets the user and begins the conversation.
     */
    public void greetUser() {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
    }

    /**
     * Ends the conversation and says bye to the user.
     *
     * @return A response string containing a goodbye message.
     */
    public static String goodbye() {
        return "Bye! It was so nice chatting with you. See you again soon!";
    }

    /**
     * Reads a command from the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
