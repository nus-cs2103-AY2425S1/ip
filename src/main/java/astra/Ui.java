package astra;

import java.util.Scanner;

/**
 * Ui class to handle user interface.
 */
public class Ui {
    private final Scanner inp;

    public Ui() {
        this.inp = new Scanner(System.in);
    }

    private static String formatMsg(String msg) {
        return "____________________________________________________________\n"
                + msg
                + "____________________________________________________________\n";
    }

    public void display(String text) {
        System.out.println(formatMsg(text));
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String msg = """
                 Hello! I'm Astra.
                 What can I do for you?
                """;
        display(msg);
    }

    /**
     * Say goodbye to the user.
     */
    public void printGoodbye() {
        String msg = " Bye. Hope to see you again soon!\n";
        display(msg);
    }

    /**
     * Shows an error message.
     *
     * @param error The error message to be displayed.
     */
    public void showError(AstraException error) {
        display(error.getMessage() + '\n');
    }

    /**
     * Reads the command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return this.inp.nextLine();
    }

    /**
     * Stops the user interface.
     */
    public void stop() {
        this.inp.close();
    }
}
