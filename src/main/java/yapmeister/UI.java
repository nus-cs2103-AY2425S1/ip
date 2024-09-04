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

    /**
     * Creates a new UI
     */
    public UI() {
        scanner = new Scanner(System.in);
        System.out.println("""
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """);
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
        System.out.println("Fine. Bye. Leave and never return");
        scanner.close();
    }

    public void exit() {
        isRunning = false;
    }

    public void displayString(String s) {
        System.out.println(s);
    }
}
