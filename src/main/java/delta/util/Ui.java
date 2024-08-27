package delta.util;

import java.util.Scanner;

/**
 * Deals with user interactions (i.e. taking in input, printing output).
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads in user input using scanner object.
     *
     * @return Next line of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a single horizontal separating line.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints welcome message for user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("""
                \t Hello! I'm Delta, your favourite Task Management Chatbot!
                \t What can I do for you?""");
        showLine();
    }

    /**
     * Prints specific message from each command.
     *
     * @param command Message containing information about command.
     */
    public void showCommand(String command) {
        System.out.println("\t " + command);
    }

    /**
     * Prints error message.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println("\t " + message);
    }
}
