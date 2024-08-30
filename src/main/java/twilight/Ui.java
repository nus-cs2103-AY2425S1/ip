package twilight;

import java.util.Scanner;

/**
 * Handles interactions with client including all reading and some printing.
 */
public class Ui {
    protected Scanner scanner;

    /**
     * Instantiates the Ui by setting a scanner object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to welcome the user upon start up.
     */
    public void greet() {
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?");
    }

    /**
     * Prints a message to indicate end of program.
     */
    public void bidFarewell() {
        System.out.println("See you");
    }

    /**
     * Prints the specified message as requested by other classes.
     *
     * @param s Message to be shown to user.
     */
    public void printMessage(String s) {
        System.out.println(s);
    }

    /**
     * Reads user input and passes it to parser.
     *
     * @return String format of user input.
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
