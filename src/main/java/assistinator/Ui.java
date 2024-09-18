package assistinator;

import java.util.Scanner;

/**
 * API of UI component.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome text.
     */
    public void showWelcome() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Assistinator.Assistinator");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
    }

    /**
     * Returns user input from scanner.
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints response.
     * @param response Response after carrying oyt command.
     */
    public void showResponse(String response) {
        System.out.println("______________________________________________");
        System.out.println(response);
        System.out.println("______________________________________________");
    }

    /**
     * Prints error message.
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println("______________________________________________");
        System.out.println(message);
        System.out.println("______________________________________________");
    }

    /**
     * Prints error message when cannot load task list.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }
}
