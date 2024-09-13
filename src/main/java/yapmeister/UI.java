package yapmeister;

import java.util.Scanner;

import yapmeister.task.TaskList;


/**
 * UI that handles user interaction
 * @author BlazeChron
 */
public class UI {
    private final Scanner scanner;
    private Parser parser;

    private boolean isRunning;
    private String currentReply = "";

    /**
     * Creates a new UI
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Starts the UI and scans user input.
     * Hands input to the Parser with processInput(input) method.
     * Ends when Parser::processInput returns false to terminate the program.
     */
    public void process() {
        String input = "";
        this.isRunning = true;
        while (this.isRunning) {
            input = this.scanner.nextLine();
            this.isRunning = this.parser.processInput(input);
        }
        this.scanner.close();
    }

    public String getResponse(String input) {
        if (this.parser.processInput(input)) {
            String ret = this.currentReply;
            this.currentReply = "";
            return ret;
        } else {
            return "Fine. Bye. Leave and never return";
        }
    }

    public void exit() {
        this.isRunning = false;
    }

    public void displayString(String s) {
        this.currentReply = this.currentReply + "\n" + s;
    }

    public String getWelcomeMessage() {
        return """
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """;
    }
}
