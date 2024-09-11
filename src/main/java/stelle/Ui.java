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

    public String wrapWithBorder(String text) {
        return HORIZONTAL_LINE + "\n" + text + "\n" + HORIZONTAL_LINE + "\n";
    }

    /**
     * Handles input and responds with output from stelle.Parser.
     * Used for text UI.
     */
    public void run() {
        printGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            showLine();
            try {
                parser.processInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            showLine();
        }
    }

    /**
     * Returns a greeting message.
     * @return String The greeting message.
     */
    public String getGreeting() {
        return "Hello! I'm " + name + ".\nWhat can I do for you?";
    }

    /**
     * Prints a greeting message.
     */
    public void printGreeting() {
        showLine();
        System.out.println("Hello! I'm " + name + ".\nWhat can I do for you?");
        showLine();
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
