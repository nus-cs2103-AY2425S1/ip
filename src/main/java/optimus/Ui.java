package optimus;

import java.util.Scanner;

/**
 * Handles all user interactions
 */
public class Ui {
    private static final String LINE_BREAK = "____________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the keyboard
     *
     * @return - user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a greeting. Should be used only upon booting up Optimus.
     */
    public void greet() {
        System.out.println("Hello! I'm optimus.Optimus\nWhat can I do for you?");
        showLineBreak();
    }

    /**
     * Prints the linebreak to the UI
     */
    public void showLineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the argument to the UI for users to see
     *
     * @param out - string to be printed
     */
    public void printToInterface(String out) {
        System.out.println(out);
    }


}
