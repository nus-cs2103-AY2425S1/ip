package Tools;

import java.util.Scanner;

/**
 * Handles user interface operations including reading user input and displaying messages.
 * This Ui class is used to handle the chatgpt version without ui page.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the scanner to read input from the standard input stream.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input read from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Checks if there is another line of input available.
     *
     * @return true if there is another line of input, false otherwise.
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Chatbot Lis. What can I do for you?");
    }

    /**
     * Closes the scanner and releases any system resources associated with it.
     */
    public void close() {
        scanner.close();
    }
}
