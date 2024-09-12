package dumpling.ui;

import java.util.Scanner;

import dumpling.DumplingException;

/**
 * Ui class to handle printing to command line
 */
public class Ui {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static final String[] WELCOME_MESSAGE = {
        "    Hello! I'm Dumpling",
        "    What can I do for you?"
    };
    private static final String EXIT_MESSAGE = "    Bye. Hope to see you again soon!";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showError(DumplingException exception) {
        this.echo("     " + exception.getMessage());
    }

    /**
     * Display welcome message of Dumpling chatbot
     */
    public void showWelcome() {
        this.echo(DIVIDER, WELCOME_MESSAGE[0], WELCOME_MESSAGE[1], DIVIDER);
    }

    /**
     * Display exit message of Dumpling chatbot
     */
    public void exit() {
        this.echo(EXIT_MESSAGE, DIVIDER);
        this.scanner.close();
    }

    public void showLine() {
        this.echo(DIVIDER);
    }

    /**
     * Prints the given messages line by line to the console
     * @param messages List of messages to be printed
     */
    public void echo(String ... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE[0] + "\n" + WELCOME_MESSAGE[1];
    }

    public String getExitMessage() {
        return EXIT_MESSAGE;
    }
}
