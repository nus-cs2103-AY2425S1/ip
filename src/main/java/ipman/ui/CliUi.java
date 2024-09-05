package ipman.ui;

import java.util.Scanner;

/**
 * Represents the command line user interface.
 * Messages will be received from standard input and messages will be displayed
 * to standard output.
 */
public class CliUi implements Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMessageF(String message, Object... args) {
        System.out.printf(message + "\n", args);
    }

    @Override
    public void showError(String message) {
        System.out.println("Oh no! Something went wrong:");
        System.out.println(message);
    }

    @Override
    public void showGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
