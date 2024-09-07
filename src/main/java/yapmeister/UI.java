package yapmeister;

import java.util.Scanner;

import yapmeister.task.TaskList;


/**
 * UI that handles user interaction
 * @author BlazeChron
 */
public class UI {
    private Scanner scanner;
    private Parser parser;
    private TaskList tasks;

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
        isRunning = true;
        while (isRunning) {
            input = scanner.nextLine();
            isRunning = parser.processInput(input);
        }
        scanner.close();
    }

    public String getResponse(String input) {
        if (parser.processInput(input)) {
            String ret = currentReply;
            currentReply = "";
            return ret;
        } else {
            return "Fine. Bye. Leave and never return";
        }
    }

    public void exit() {
        isRunning = false;
    }

    public void displayString(String s) {
        //System.out.println(s);
        currentReply = currentReply + "\n" + s;
    }

    public String getWelcomeMessage() {
        return """
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """;
    }
}
