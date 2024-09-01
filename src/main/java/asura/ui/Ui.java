package asura.ui;

import java.util.Scanner;

/**
 * Represents the UI of the program.
 */
public class Ui {

    private Scanner scanner;
    private String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";
    private String goodbye = """
                Bye. Hope to see you again soon!""";

    /**
     * Creates a UI.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Outputs the introduction.
     */
    public void showIntroduction() {
        System.out.println(formatResponse(introduction));
    }

    /**
     * Outputs the goodbye.
     */
    public void showGoodbye() {
        System.out.println(formatResponse(goodbye));
    }

    /**
     * Outputs the error that is specified.
     * @param error The error specified.
     */
    public void showError(String error) {
        System.out.println(formatResponse(error));
    }

    /**
     * Reads the next input of the user.
     * @return The input of the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Outputs and formats the string specified.
     * @param s The string specified.
     */
    public void printString(String s) {
        System.out.println(formatResponse(s));
    }

    /**
     * Formats the string to include heading and footing lines.
     * @param msg The string to be formatted.
     * @return The formatted string.
     */
    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
