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

    private String output = "";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Outputs a greeting to the user
     */
    public void showGreet() {
        System.out.println("  " + GREET);
        output = output.concat(GREET);
        output = output.concat(System.lineSeparator());
    }

    /**
     * Outputs a bye to the user
     */
    public void showBye() {
        System.out.println("  " + BYE);
        output = output.concat(BYE);
        output = output.concat(System.lineSeparator());
    }

    /**
     * Outputs the result of the command
     *
     * @param result a <code>String</code> corresponds to the result
     */
    public void showResult(String result) {
        System.out.println("  " + result);
        if (!output.isEmpty()) {
            output = output.concat(System.lineSeparator());
        }
        output = output.concat(result);
    }

    public String flushOutput() {
        String tmp = output;
        output = "";
        return tmp;
    }

    /**
     * Reads an input from the user
     *
     * @return a <code>String</code> corresponds to the input from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
