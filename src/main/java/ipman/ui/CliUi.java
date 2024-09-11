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
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMessageFormat(String message, Object... args) {
        System.out.printf(message + "\n", args);
    }

    @Override
    public void showError(String message) {
        System.out.println("Oh no! Something went wrong:");
        System.out.println(message);
    }
}
