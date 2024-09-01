package jbot.util;

import java.util.Scanner;

import jbot.command.JBotCommand;

/**
 * A utility class for handling user interface interactions. This class cannot be instantiated.
 */
public class Ui {
    private Ui() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Closes the scanner used for reading user input.
     */
    public static void close() {
        sc.close();
    }

    private static void hLine() {
        System.out.println("________________________________________");
    }

    /**
     * Displays a greeting message to the user.
     */
    public static void greetUser() {
        hLine();
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
        hLine();
    }

    /**
     * Displays the result of executing a command and the user input.
     *
     * @param command The command to execute.
     * @param userInput The input provided by the user.
     */
    public static void display(JBotCommand command, String userInput) {
        hLine();
        command.run(userInput);
        hLine();
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input read from the user.
     */
    public static String readInput() {
        return sc.nextLine();
    }

    /**
     * Handles and displays an error message to the user.
     *
     * @param e The exception containing the error message.
     */
    public static void handleError(Exception e) {
        hLine();
        System.out.println(e.getMessage());
        hLine();
    }
}