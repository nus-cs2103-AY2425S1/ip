package bob;

import java.util.Scanner;

/**
 * The {@code Ui} serves as the user interface for the Bob application.
 * It is responsible for displaying messages to the user and reading raw user input.
 */
public class Ui {
    private static final String[] welcome = {
            "Hello! I'm bob.Bob", "What can I do for you?"
    };
    private static final String[] farewell = {
            " Bye. Hope to see you again soon!"
    };

    /**
     * Displays the welcome message to the user.
     */
    protected void showWelcome() {
        Printer.prettyPrint(Ui.welcome);
    }

    /**
     * Displays the farewell message to the user.
     */
    protected void showFarewell() {
        Printer.prettyPrint(Ui.farewell);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg the error message to display.
     */
    protected void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads a command from the user input.
     *
     * @return the command entered by the user as a {@code String}.
     */
    protected String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
