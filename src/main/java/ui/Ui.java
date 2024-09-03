package ui;

import java.util.Scanner;

/**
 * Class that reads the raw user command.
 * It also displays the response of the chatbot.
 */
public class Ui {
    private static final String HORIZONTAL_LINE =
            "_____________________________________________________________________\n";

    /**
     * Reads the raw user command, from the {@code Scanner} object.
     *
     * @param s {@code Scanner} object to read from.
     * @return The processed user command.
     */
    public String readCommand(Scanner s) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return s.nextLine()
                .trim()
                .replaceAll(" +", " ");
    }

    /**
     * Displays the initial welcome response.
     */
    public void displayInitialResponse() {
        displayResponse("Hello! I'm Brock\n"
                + "What can I do for you?");
    }

    /**
     * Displays a particular response.
     *
     * @param response Response to be displayed.
     */
    public void displayResponse(String response) {
        System.out.println(HORIZONTAL_LINE
                + response
                + '\n'
                + HORIZONTAL_LINE);
    }
}
