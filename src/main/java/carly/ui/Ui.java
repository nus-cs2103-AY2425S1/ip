package carly.ui;

import carly.exception.CarlyException;

import java.util.Scanner;

public class Ui {
    private final String username;
    private static final String LINE = "-----------------------------------------------------------";
    public static final String INDENTATION = "    ";

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

    public String ReadInput(Scanner scan) throws CarlyException {
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
        System.out.println(INDENTATION + LINE + "\n" +  INDENTATION + message + "\n");
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

