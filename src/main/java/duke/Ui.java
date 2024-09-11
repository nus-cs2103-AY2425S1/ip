package duke;

import java.util.Scanner;

/**
 * Handles the user interface interactions.
 * Provides methods to display messages and read user input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + "Meow" + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public static String getWelcomeMessage() {
        return " Hello! I'm " + "Meow" + "\n" +
                " What can I do for you?\n";
    }
    /**
     * Displays the goodbye message when the application closes.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command input by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message that is not an error.
     *
     * @param message The message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getMessage(String message) {
        return message;
    }
}
