package tohru.ui;

import java.util.Scanner;

/**
 * Represents the user interface that the user interacts with the chatbot.
 */
public class Tui implements Ui {

    /** Scanner for receiving user input. */
    private final Scanner userInput;

    /**
     * Initialises the Ui object.
     */
    public Tui() {
        // Setup scanner to receive user input
        userInput = new Scanner(System.in);
    }

    /**
     * Retrieves user input.
     *
     * @return command entered by user.
     */
    public String readCommand() {
        String prompt = "";
        while (prompt.isBlank()) {
            prompt = userInput.nextLine();
        }
        return prompt;
    }

    /**
     * Closes the scanner.
     */
    public void closeInput() {
        userInput.close();
    }

    /**
     * Prints dividers.
     */
    public void showDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints text output from commands.
     */
    public void showText(String ...text) {
        for (String msg : text) {
            System.out.println(msg);
        }
    }

    /**
     * Prints errors from commands.
     */
    public void showError(String ...error) {
        for (String msg : error) {
            System.out.printf("Error | %s%n", msg);
        }
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showDivider();
        System.out.println("Hello! I'm Tohru");
        System.out.println("What can I do for you?");
        showDivider();
    }

}
