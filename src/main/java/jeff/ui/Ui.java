package jeff.ui;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL =
            "_____________________________________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("");
        return scanner.nextLine();
    }

    public void showLoadingError() {
        this.printText("Oops! There is something wrong with loading the tasks!");
    }

    public void showError(String message) {
        this.printText(message);
    }

    /**
     * Prints out a greeting message.
     */
    public void printWelcome() {
        printText("Hello! I'm Jeff.\n What can I do for you?");
    }

    /**
     * Prints out a farewell message and close the scanner.
     */
    public void printFarewell() {
        this.scanner.close();
        printText("Bye. Hope to see you again soon!");
    }

    /**
     * Encloses the text with horizontal lines and indents the text before printing it out
     *
     * @param text The string to be enclosed
     */
    public void printText(String text) {
        System.out.println(indentText(HORIZONTAL + "\n " + text + "\n" + HORIZONTAL));
    }

    /**
     * Returns the same text but indented. If the text has multiple lines, each line will be indented.
     *
     * @param text the string to be indented
     * @return indented text
     */
    public String indentText(String text) {
        // Split the text into lines
        String[] lines = text.split("\n");

        // Initialise a StringBuilder
        StringBuilder indentedText = new StringBuilder();

        // Add indentation to each line
        for (String line : lines) {
            indentedText.append("\t").append(line).append("\n");
        }

        // Convert the StringBuilder back to a String
        return indentedText.toString();

    }
}
