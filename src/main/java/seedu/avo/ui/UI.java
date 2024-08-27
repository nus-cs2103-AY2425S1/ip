package seedu.avo.ui;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message
     */
    public void showWelcome() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    /**
     * Displays an exit message
     */
    public void showExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    /**
     * Displays an error message
     */
    public void showError(String message) {
        System.out.println(message);
    }
    /**
     * Reads user input from CLI
     * @return A raw input from the user
     */
    public String readInput() {
        String input = "exit";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
