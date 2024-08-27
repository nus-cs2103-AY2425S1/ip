import java.util.Scanner;

/**
 * Ui represents the user interface that the user interacts with the chatbot
 */
public class Ui {

    /** Scanner for receiving user input **/
    private final Scanner userInput;

    public Ui() {
        // Setup scanner to receive user input
        userInput = new Scanner(System.in);
    }

    /**
     * Retrieve user input
     *
     * @return command entered by user
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
     * Helper function to print dividers
     */
    public void showDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Helper function to print text output from commands
     */
    public void showText(String text) {
        System.out.println(text);
    }

    /**
     * Helper function to print errors from commands
     */
    public void showError(String error) {
        System.out.printf("Error | %s%n", error);
    }

    /**
     * Helper function to print welcome message
     */
    public void showWelcome() {
        showDivider();
        System.out.println("Hello! I'm Tohru");
        System.out.println("What can I do for you?");
        showDivider();
    }

}
