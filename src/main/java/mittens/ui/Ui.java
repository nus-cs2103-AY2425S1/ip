package mittens.ui;

import mittens.MittensException;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public abstract class Ui {
    protected final Scanner in;

    public Ui(Scanner in) {
        this.in = in;
    }

    /**
     * Shows the greeting message to be displayed at the start of the program.
     */
    public abstract void showGreetingMessage();

    /**
     * Shows the goodbye message to be displayed when the user exits the program.
     */
    public abstract void showGoodbyeMessage();

    /**
     * Generates a combined message formed by the given list of strings.
     * 
     * @param messages The list of strings to output
     */
    public abstract void showRegularMessage(List<String> messages);

    /**
     * Generates a combined message accompanied by the cute cat Mittens.
     * 
     * @param messages The list of strings to output
     */
    public abstract void showMittensMessage(List<String> messages);

    /**
     * Generates an error message based on the given throwable.
     * 
     * @param e The exception to output
     */
    public abstract void showErrorMessage(MittensException e);

    /**
     * Waits for the user to input a string command and returns that string.
     *
     * @return The user input
     */
    public String getUserInput() {
        return this.in.nextLine();
    }

    /**
     * Generates a combined message formed by the given strings.
     *
     * @param messages The strings to output
     */
    public void showRegularMessage(String... messages) {
        showRegularMessage(List.of(messages));
    };

    /**
     * Generates a combined message accompanied by the cute cat Mittens.
     *
     * @param messages The strings to output
     */
    public void showMittensMessage(String... messages) {
        showMittensMessage(List.of(messages));
    };
}
