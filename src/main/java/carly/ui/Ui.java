package carly.ui;

import java.util.Scanner;

import carly.exception.CarlyException;


/**
 * Deals with interactions with the user.
 * Also deals with formatting of output message.
 */
public class Ui {
    public static final String INDENTATION = "    ";
    private static final String LINE = "-----------------------------------------------------------";
    private final String username;

    public Ui(String username) {
        this.username = username;
    }

    public Ui() {
        this("");
    }

    public Ui setUsername(Scanner scan) {
        printOutput("What is your name?");
        String username = scan.nextLine();
        return new Ui(username);
    }

    /** Displays a welcome message to the user with the current username. */
    public void welcomeMsg() {
        printOutput("Hey " + username + "! I'm Carly.\n" + INDENTATION + "What can I do for you?");
    }

    /** Displays a farewell message to the user with the current username. */
    public void byeMsg() {
        printOutput("Bye " + username + ". I'll see you next time!");
    }

    /** Reads input when user types into the chatbot. */
    public String readInput(Scanner scan) throws CarlyException {
        if (scan.hasNextLine()) {
            return scan.nextLine();
        } else {
            throw new CarlyException("");
        }
    }

    public void printLineSeparator() {
        printOutput(Ui.LINE);
    }

    /**
     * Prints output like:
     *     -----
     *     text here
     *     ----
     */
    public static void printOutput(String message) {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + message + "\n" + INDENTATION + LINE);
    }

    /**
     * Prints output like:
     *     -----
     *     text here
     */
    public static void printOutputTopLine(String message) {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + message + "\n");
    }

    /**
     * Prints output like:
     *     text here
     *     ----
     */
    public static void printOutputBottomLine(String message) {
        System.out.println(INDENTATION + message + "\n" + INDENTATION + LINE);
    }

    public static void printOutputBottomLine() {
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints output like:
     *     text here
     */
    public static void printOutputNoLine(String message) {
        System.out.println(INDENTATION + message);
    }

    public static String getOutputForException(String message) {
        return INDENTATION + Ui.LINE + "\n" + INDENTATION + message + "\n" + INDENTATION + LINE;
    }
}

