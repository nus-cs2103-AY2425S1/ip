package james;

import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and reading input.
 */
class Ui {

    /**
     * Displays a greeting message to the user.
     */
    public String showGreeting() {
        return "Hello Big Boy! I'm James\nHow can I assist you today?";
    }

    /**
     * Displays an exit message to the user.
     */
    public String showExitMessage() {
        return "Goodbye. Come back anytime!";
    }

    /**
     * Displays a message to the user.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
