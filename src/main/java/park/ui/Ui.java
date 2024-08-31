package park.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents an object that deals with user inputs.
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a Ui object that takes inputs from the user and prints output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui object.
     *
     * @param in InputStream object.
     * @param out PrintStream object.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the user input.
     *
     * @return User input as a String.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Prints an output for the user to see.
     *
     * @param message String to be printed.
     */
    public void showToUser(String message) {
        out.println(message);
    }

    /**
     * Prints welcome message upon running the chatbot
     */
    public void showWelcomeMessage() {
        showToUser("""
                Hello! I'm Park
                What can I do for you?""");;
    }

    /**
     * Prints goodbye message upon exiting the chatbot
     */
    public void showGoodbyeMessage() {
        showToUser("Bye. Hope to see you again soon!");
    }
}
