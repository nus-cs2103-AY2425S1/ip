package duke;

import java.util.Scanner;

/**
 * Handles the user interface interactions.
 * Provides methods to display messages and read user input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

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
