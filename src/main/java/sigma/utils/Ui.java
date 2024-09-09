package sigma.utils;

import java.util.Scanner;

import sigma.exception.SigmaException;

/**
 * Represents the user interface of Sigma.
 * Contains methods to read user input and print messages.
 */
public class Ui {

    private final Scanner input = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public static String welcome() {
        return "What the sigma? I'm Sigma, your personal chat assistant! How can I help you today?\n"
                + "Type \"help\" to see the list of commands!\n"
                + "Otherwise, "
                + "enter \"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\", \"find\" or \"bye\"!\n"
                + "Sigma, sigma, on the wall, who is the skibidiest of them all?";
    }

    /**
     * Prints the exit message.
     */
    public static String exit() {
        return "What the sigma? You're leaving so soon? Bye chat, see you again!";
    }

    /**
     * Throws an unrecognised error message.
     *
     * @throws SigmaException Unrecognised error message.
     */
    public static void throwUnrecognisedError() throws SigmaException {
        throw new SigmaException("What the sigma? I don't understand! Try again! Enter "
                + "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\", \"find\" or \"bye\"!");
    }

    /**
     * Throws an error message.
     *
     * @param message Error message.
     *
     * @throws SigmaException Error message.
     */
    public static void throwError(String message) throws SigmaException {
        throw new SigmaException(message);
    }

    /**
     * Builds a list of tasks.
     *
     * @param tasks Task list.
     *
     * @return StringBuilder containing a string representation of a list of tasks.
     */
    public static StringBuilder buildList(TaskList tasks) {
        return tasks.buildList();
    }

}
