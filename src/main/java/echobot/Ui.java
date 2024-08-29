package echobot;

import java.util.Scanner;

/**
 * Handles user interaction with the EchoBot application.
 * Manages the display of messages and user input.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the initial greeting message to the user.
     * Informs the user that the EchoBot is ready to accept commands.
     */
    public void start() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm EchoBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The input line provided by the user.
     */
    public String nextInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the exit message to the user and closes the scanner.
     * Informs the user that the application is closing.
     */
    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
