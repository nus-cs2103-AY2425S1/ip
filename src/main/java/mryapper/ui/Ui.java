package mryapper.ui;

import java.util.Scanner;

/**
 * A class in charge of the user interface and sending messages to the user.
 */
public class Ui {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner scanner;

    /**
     * Initializes the scanner and user interface of the Chatbot.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next user input.
     *
     * @return The user input in the form of a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used to read user inputs.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Sends a message of the user using println.
     *
     * @param message The message to be sent to the user.
     */
    public void send(String message) {
        System.out.println(message);
    }

    /**
     * Prints out the line separator around the messages sent by the Chatbot.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the Chatbot send the greeting/hello message.
     */
    public String getGreeting() {
        return GREETING_MESSAGE;
    }

    /**
     * Makes the Chatbot send the goodbye message.
     */
    public String getGoodbye() {
        return GOODBYE_MESSAGE;
    }
}
