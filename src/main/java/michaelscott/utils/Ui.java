package michaelscott.utils;

import java.util.Scanner;

/**
 * This class represents user interface of MichaelScott.
 * Handles user input and output display.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        println("Running MichaelScott.exe");
        printLine();
        println("Hello! I'm Michael Scott");
        println("What can I do for you?");
        printLine();
    }

    /**
     * Prints a good bye message.
     */
    public void showGoodBye() {
        printLine();
        println("Catch you on the flippity flip!");
        printLine();
        this.sc.close();
    }

    /**
     * Shows the error message.
     */
    public static void showError(String message) {
        printLine();
        println(message);
        printLine();
    }

    /**
     * Displays loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading data. Starting with an empty task list.");
    }

    /**
     * Displays response after a command is executed.
     */
    public void showResponse(String message) {
        printLine();
        println(message);
        printLine();
    }

    /**
     * reads a command entered by the user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a line.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Just shortening system.out.println.
     */
    public static void println(String sentence) {
        System.out.println(sentence);
    }
}
