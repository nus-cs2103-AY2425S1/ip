package Dook.Ui;

import java.util.Scanner;

/**
 *  The Ui class deals with interactions with the user.
 */
public class Ui {

    private static final String separator = "____________________________________________________________";
    private static final String greeting = "Hello! I'm Dook.Dook\nWhat can I do for you?\n" + separator;
    private static final String exit = "Bye. Hope to see you again soon!\n" + separator;

    private final Scanner scanner = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Prints the line separator.
     */
    public void separate() {
        System.out.println(separator);
    }

    /**
     * Prints the chatbot's greeting message.
     */
    public void greet() {
        System.out.println(separator);
        System.out.println(greeting);
    }

    /**
     * Prints the message when the user exits the chatbot.
     */
    public void exit() {
        System.out.println(separator);
        System.out.println(exit);
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
        System.out.println(separator);
        System.out.println(errorMessage);
        System.out.println(separator);
    }

}
