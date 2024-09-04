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
     * Prints text messages from commands.
     *
     * @param texts Messages to be printed.
     */
    public void showText(String ...texts) {
        for (String msg : texts) {
            System.out.println(msg);
        }
    }

    /**
     * Prints error messages from commands.
     *
     * @param errors Error messages to be printed.
     */
    public void showError(String ...errors) {
        for (String msg : errors) {
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
