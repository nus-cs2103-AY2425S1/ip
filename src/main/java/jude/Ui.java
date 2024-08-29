package jude;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles the interaction with the user.
 *
 * Displays an instruction or result of user's input on the screen so that the user can keep track of the current state.
 * Shows an error if there is any.
 * Reads input from the user.
 */
public class Ui {
    private BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

    /** Displays the error message. */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void startChat() {
        // Initiate the chat
        System.out.println("Hello I'm jude.Jude");
        System.out.println("What can I do for you, poyo?");
    }

    public void endChat() {
        System.out.println("Poyo. Hope to see you again soon!");
    }

    /**
     * Read the user input.
     * @return the user input as String.
     * @throws JudeException if there is any IOException.
     */
    public String readCommand() throws JudeException {
        try {
            return bi.readLine();
        } catch (IOException ie) {
            throw new JudeException("IO operation was failed or interrupted. Please try again, poyo...");
        }
    }

    public void showLine() {
        System.out.println("----------------------------------------------------------------------------------");
    }
}
