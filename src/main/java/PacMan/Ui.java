package pacman;

import java.util.Scanner;

/**
 * Implements the interaction in the terminal. A <code>Ui</code> object
 * corresponds to a <code>Scanner</code> that is used for reading input from stdin
 */
public class Ui {
    private static final String GREET = "Hello! I'm PacMan. How can I help you?";
    private static final String BYE = "Good bye. Hope to see you soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Output a greeting to the user
     */
    public void showGreet() {
        System.out.println("  " + GREET);
    }

    /**
     * Output a bye to the user
     */
    public void showBye() {
        System.out.println("  " + BYE);
    }

    /**
     * Output the result of the command
     *
     * @param result a <code>String</code> corresponds to the result
     */
    public void showResult(String result) {
        System.out.println("  " + result);
    }

    /**
     * Read an input from the user
     *
     * @return a <code>String</code> corresponds to the input from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
