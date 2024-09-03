package sigma;

import sigma.exception.SigmaException;

import java.util.Scanner;

/**
 * Represents the user interface of Sigma.
 * Contains methods to read user input and print messages.
 */
public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Reads the user input.
     * @return User input.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public static void welcome() {
        print("Hello! I'm SIGMA! \nLooking forward to slaying with you!\nWhat do you need today?" +
                "\nYou can add tasks with \"todo\", \"deadline\", \"event\" or view tasks with \"list\"." +
                "\nYou can also mark tasks as done with \"mark\" or \"unmark\" them." +
                "\nYou can search for tasks with \"find\"." +
                "\nYou can also delete tasks with \"delete\"." +
                "\nIf you're done, just type \"bye\" to exit.");
    }

    /**
     * Prints the exit message.
     */
    public static void exit() {
        print("What the sigma? You're leaving so soon? Bye chat, see you again!");
    }

    /**
     * Prints the message.
     * @param message Message to be printed.
     */
    public static void print(String message) {
        line();
        System.out.println(message);
        line();
    }

    /**
     * Prints a line.
     */
    private static void line() {
        String line = "----------------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    /**
     * Throws an unrecognised error message.
     * @throws SigmaException Unrecognised error message.
     */
    public static void throwUnrecognisedError() throws SigmaException {
        throw new SigmaException("What the sigma? I don't understand! Try again! Enter " +
                "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\", \"find\" or \"bye\"!");
    }

    /**
     * Throws an error message.
     * @param message Error message.
     * @throws SigmaException Error message.
     */
    public static void throwError(String message) throws SigmaException {
        throw new SigmaException(message);
    }

    /**
     * Builds a list of tasks.
     * @param tasks Task list.
     * @return StringBuilder containing a string representation of a list of tasks.
     */
    public static StringBuilder buildList(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            s.append(i + ". " + tasks.get(i - 1).toString() + "\n");
        }
        return s;
    }
    
}
