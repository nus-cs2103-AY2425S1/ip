package alisa;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Constructs an instance of Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }


    /**
     * Prints an error message.
     *
     * @param errorMessage The error message
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a goodbye message.
     *
     * @return A goodbye message.
     */
    public String showByeMessage() {
        return "Since you technically said bye, see ya next time!";
    }

    /**
     * Returns a welcome message.
     *
     * @return A welcome message.
     */
    public String showWelcomeMessage() {
        return "Hey, Alisa here! What do you need help with?\n" + "Just say the word \"bye\" to get out of this conversation";
    }
}
