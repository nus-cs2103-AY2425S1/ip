package dumpling.Ui;

import dumpling.Dumpling;
import dumpling.DumplingException;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static final String WELCOME_MESSAGE = "    Hello! I'm Dumpling\n" +
            "    What can I do for you?";
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

    public void showLine() {
        this.echo(DIVIDER);
    }

    public void showWelcome() {
        this.showLine();
        this.echo(WELCOME_MESSAGE);
        this.showLine();
    }

    public void exit() {
        this.echo(EXIT_MESSAGE);
        this.scanner.close();
        this.showLine();
    }

    public void echo(String message) {
        System.out.println(message);
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String getExitMessage() {
        return EXIT_MESSAGE;
    }
}
