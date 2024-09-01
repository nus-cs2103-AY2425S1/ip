package astor;

import java.util.Scanner;

/**
 * This class represents the user interface and the interactions to be conducted with the user.
 */
public class Ui {
    private final String SEPARATOR_LINE = "--------------------------------------";
    private final String INTRODUCTION_LINE = "Hello, I'm Astor!\n" + "What can I do for you?\n"
            + SEPARATOR_LINE;
    private Parser parser;
    private Scanner scanner;

    public Ui() {
        parser = new Parser();
    }

    /**
     * Displays the default welcome statement.
     */
    public void showWelcome() {
        System.out.println(INTRODUCTION_LINE);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Tracks user input
     * @return user input
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    /**
     * Displays a dotted line separating blocks of inputs and outputs
     */
    public void showLine() {
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays error message
     *
     * @param message an error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays output message
     *
     * @param output the output message
     */
    public void showOutput (String output) {
        System.out.println(output);
    }


}
