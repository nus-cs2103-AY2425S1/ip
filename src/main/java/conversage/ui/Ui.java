package conversage.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String logo = "  ____                          ____                   \n" +
                      " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                      "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _ |/ _ |/ _ \\\n" +
                      "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                      " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                      "                                            |___/      ";
        
        System.out.println(logo);
        System.out.println("Greetings, I'm your Conversage.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Goodbye. We shall meet again soon.");
        showLine();
    }

    /**
     * Shows a line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________ \n");
    }

    /**
     * Shows an error message.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println("ERR: " + message);
        showLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a message to the user.
     *
     * @param message the message to display.
     */

    public void showMessage(String message) {
        System.out.println(message);

    }


}
