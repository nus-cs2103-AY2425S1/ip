package myapp.core;

import java.util.Scanner;

/**
 * Represents the User Interface (UI) for the BingBong application.
 * This class handles interactions with the user, such as displaying
 * messages and reading user input.
 */
public class BingBongUi {
    private final Scanner scanner;

    /**
     * Constructs a BingBongUI object that reads input from the standard input stream.
     */
    public BingBongUi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line separator to the user.
     */
    public void showHorizontalLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        showHorizontalLine();
        System.out.println("Hello! I'm BingBong\n" + "What can I do for you?");
        showHorizontalLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        showHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        showHorizontalLine();
    }

    /**
     * Reads a command from the user input.
     *
     * @return the command entered by the user.
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Displays a response message to the user, surrounded by horizontal lines.
     *
     * @param response the response message to be displayed.
     */
    public void showResponse(String response) {
        showHorizontalLine();
        System.out.println(response);
        showHorizontalLine();
    }
}
