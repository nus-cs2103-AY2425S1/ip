package MichaelScott.Ui;

import java.util.Scanner;


/**
 * Handles interactions with the user,
 * providing a user interface.
 */
public class Ui {
    Scanner sc;

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user when the program starts.
     */
    public void showWelcome() {
        println("Running MichaelScott.MichaelScott.exe");
        printLine();
        println("Hello! I'm Michael Scott");
        println("What can I do for you?");
        printLine();
    }

    /**
     * Displays the goodbye message to the user when the program ends
     * and also close the scanner.
     */
    public void showGoodBye() {
        printLine();
        println("Catch you on the flippity flip!");
        printLine();
        this.sc.close();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printLine();
        println(message);
        printLine();
    }

    /**
     * Displays a loading error message to the user when there is an issue loading data.
     */
    public void showLoadingError() {
        System.out.println("Error loading data. Starting with an empty task list.");
    }

    /**
     * Displays a response message to the user
     * after enclosing the message in lines.
     *
     * @param message The response message to be displayed.
     */
    public void showResponse(String message) {
        printLine();
        println(message);
        printLine();
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a horizontal line for separating sections of text in the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a line of text to the console.
     *
     * @param sentence The sentence to be printed.
     */
    public static void println(String sentence) {
        System.out.println(sentence);
    }
}
