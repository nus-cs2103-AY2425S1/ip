package stelle;

import java.util.Scanner;

/**
 * Represents the UI handler of the chatbot.
 * @author Lee Ze Hao (A0276123J)
 */

public class Ui {
    static final String HORIZONTAL_LINE = "____________________________________________________________";

    private String name;
    private Parser parser;

    /**
     * Creates a stelle.Ui handler instance.
     * @param name Name of the chatbot.
     */
    public Ui(String name, String filePath) {
        this.name = name;
        this.parser = new Parser(filePath);
    }

    public String getResponse(String input) {
        try {
            return parser.processInput(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a greeting message.
     * @return String The greeting message.
     */
    public String getGreeting() {
        return "Hello! I'm " + name + " (simulated).\nWhat can I do for you?";
    }

    /**
     * Prints a goodbye/ending message.
     */
    public void printBye() {
        System.out.println("Bye.");
        showLine();
    }

    /**
     * Prints a horizontal line / border.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
