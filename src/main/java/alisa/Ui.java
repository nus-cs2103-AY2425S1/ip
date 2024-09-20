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
     * Takes in and returns an input from user.
     *
     * @return Input from user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a line divider.
     */
    public void showDivider() {
        System.out.println("--------------------------------------------------------------");
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage The error message
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        showDivider();
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hey, alisa.Alisa here! What do you need help with?");
        System.out.println("BTW Say the word bye to get out of this conversation");
        showDivider();
    }

    /**
     * Prints a goodbye message.
     *
     * @return
     */
    public String showByeMessage() {
        return "Since you technically said bye, see ya next time!";
    }

    /**
     * Prints a message.
     *
     * @param message Message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
        showDivider();
    }

}
