package dook.ui;

import java.util.Scanner;

/**
 *  The Ui class deals with interactions with the user.
 */
public class Ui {

    private static final String SEPARATOR = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Dook\nWhat can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";

    private final Scanner scanner = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Prints the line separator.
     */
    public void separate() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the chatbot's greeting message.
     */
    public String greet() {
        System.out.println(SEPARATOR);
        System.out.println(GREETING);
        System.out.println(SEPARATOR);
        return GREETING;
    }

    /**
     * Prints the message when the user exits the chatbot.
     */
    public String exit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT);
        System.out.println(SEPARATOR);
        return EXIT;
    }

    /**
     * Returns the user's command input.
     *
     * @return String of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints out the String passed in as the parameter.
     *
     * @param message Message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints out the String passed in as the parameter.
     * For the printing of error messages.
     *
     * @param errorMessage Error message to be printed.
     */
    public void errorMessage(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(errorMessage);
        System.out.println(SEPARATOR);
    }

}
